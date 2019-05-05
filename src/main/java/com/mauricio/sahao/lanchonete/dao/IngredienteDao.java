package com.mauricio.sahao.lanchonete.dao;

import com.mauricio.sahao.lanchonete.model.Ingrediente;
import org.springframework.data.repository.CrudRepository;

public interface IngredienteDao extends CrudRepository<Ingrediente,Long> {
}
