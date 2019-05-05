package com.mauricio.sahao.lanchonete.service;

import com.mauricio.sahao.lanchonete.model.Ingrediente;
import com.mauricio.sahao.lanchonete.model.dto.AtualizarPrecoDTO;

public interface IngredienteService extends GenericService<Ingrediente,Long> {

    void atualizarPreco(AtualizarPrecoDTO atualizarPrecoDTO);

    Iterable<Ingrediente> incluirIngredientes();
}
