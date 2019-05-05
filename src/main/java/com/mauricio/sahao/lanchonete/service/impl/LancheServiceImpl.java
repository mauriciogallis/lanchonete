package com.mauricio.sahao.lanchonete.service.impl;

import com.mauricio.sahao.lanchonete.dao.LancheDao;
import com.mauricio.sahao.lanchonete.model.Lanche;
import com.mauricio.sahao.lanchonete.service.IngredienteService;
import com.mauricio.sahao.lanchonete.service.LancheIngredienteService;
import com.mauricio.sahao.lanchonete.service.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LancheServiceImpl extends GenericServiceImpl<Lanche,Long> implements LancheService {

    @Autowired
    private LancheDao dao;

    @Autowired
    private LancheIngredienteService lancheIngredienteService;

    @Autowired
    private IngredienteService ingredienteService;

    @Override
    protected CrudRepository<Lanche, Long> getDao() {
        return dao;
    }

    public void save(Lanche lanche){
         dao.save(lanche);
         salvarIngredientes(lanche);
    }

    private void salvarIngredientes(Lanche lanche){
        lanche.getIngredientes().forEach(ingreditente -> {
            ingreditente.setIdLanche(lanche.getId());
            lancheIngredienteService.save(ingreditente);
        });
    }

    @Transactional
    public Optional<Lanche> findByIdEager(Long id){
        Optional<Lanche> lanche = dao.findById(id);
        lanche.ifPresent(l -> l.getIngredientes().forEach(s -> {
            s.setIngrediente(ingredienteService.findById(s.getIdIngrediente()).get());
        }));
        return lanche;
    }


}
