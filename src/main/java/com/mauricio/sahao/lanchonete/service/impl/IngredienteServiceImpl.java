package com.mauricio.sahao.lanchonete.service.impl;

import com.mauricio.sahao.lanchonete.dao.IngredienteDao;
import com.mauricio.sahao.lanchonete.model.Ingrediente;
import com.mauricio.sahao.lanchonete.model.dto.AtualizarPrecoDTO;
import com.mauricio.sahao.lanchonete.model.enums.TipoIngrediente;
import com.mauricio.sahao.lanchonete.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class IngredienteServiceImpl extends GenericServiceImpl<Ingrediente,Long> implements IngredienteService {

    private final IngredienteDao dao;

    @Autowired
    public IngredienteServiceImpl(IngredienteDao dao) {
        this.dao = dao;
    }

    @Override
    protected CrudRepository<Ingrediente, Long> getDao() {
        return dao;
    }

    @Override
    public void atualizarPreco(AtualizarPrecoDTO dto) {
        if(Objects.isNull(dto.getIdIngrediente())){
            atualizaTodosIngredientes(dto);
        }else{
            atualizarIngrediente(dto);
        }
    }

    private void atualizarIngrediente(AtualizarPrecoDTO dto) {
        Optional<Ingrediente> ingrediente = dao.findById(dto.getIdIngrediente());
        if(ingrediente.isPresent()) {
            adicionaAcrescimo(dto, ingrediente.get());
            subtraiDesconto(dto, ingrediente.get());
            dao.save(ingrediente.get());
        }
    }

    private void atualizaTodosIngredientes(AtualizarPrecoDTO atualizarPrecoDTO) {
        dao.findAll().forEach( s->{
            adicionaAcrescimo(atualizarPrecoDTO, s);
            subtraiDesconto(atualizarPrecoDTO, s);
            dao.save(s);
        });
    }

    private void subtraiDesconto(AtualizarPrecoDTO atualizarPrecoDTO, Ingrediente s) {
        if(Objects.nonNull(atualizarPrecoDTO.getPercentualDesconto())){
            s.setPreco(s.getPreco().subtract(s.getPreco().divide(new BigDecimal(100),4,BigDecimal.ROUND_CEILING)
                    .multiply(atualizarPrecoDTO.getPercentualDesconto())));
        }
    }

    private void adicionaAcrescimo(AtualizarPrecoDTO atualizarPrecoDTO, Ingrediente s) {
        if(Objects.nonNull(atualizarPrecoDTO.getPercentualAcrescimo())){
            s.setPreco(s.getPreco().add(s.getPreco().divide(new BigDecimal(100),4,BigDecimal.ROUND_CEILING)
                    .multiply(atualizarPrecoDTO.getPercentualAcrescimo())));
        }
    }

    public Iterable<Ingrediente> incluirIngredientes(){
        if(dao.count() == 0) {
            dao.save(new Ingrediente(null, "Alface", new BigDecimal("0.40"), TipoIngrediente.ALFACE));
            dao.save(new Ingrediente(null, "Bacon", new BigDecimal("2"), TipoIngrediente.BACON));
            dao.save(new Ingrediente(null, "Queijo", new BigDecimal("1.50"), TipoIngrediente.QUEIJO));
            dao.save(new Ingrediente(null, "Ovo", new BigDecimal("0.80"), TipoIngrediente.OVO));
            dao.save(new Ingrediente(null, "Hamburger", new BigDecimal("3.00"), TipoIngrediente.HAMBURGUER));
        }
        return dao.findAll();
    }

}
