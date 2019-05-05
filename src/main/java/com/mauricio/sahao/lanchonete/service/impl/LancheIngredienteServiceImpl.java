package com.mauricio.sahao.lanchonete.service.impl;

import com.mauricio.sahao.lanchonete.dao.LancheIngredienteDao;
import com.mauricio.sahao.lanchonete.model.LancheIngrediente;
import com.mauricio.sahao.lanchonete.service.LancheIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LancheIngredienteServiceImpl extends GenericServiceImpl<LancheIngrediente,Long> implements LancheIngredienteService {

    private final LancheIngredienteDao dao;

    @Autowired
    public LancheIngredienteServiceImpl(LancheIngredienteDao dao) {
        this.dao = dao;
    }

    @Override
    protected CrudRepository<LancheIngrediente, Long> getDao() {
        return dao;
    }
}
