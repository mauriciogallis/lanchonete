package com.mauricio.sahao.lanchonete.dao;

import com.mauricio.sahao.lanchonete.model.Lanche;
import org.springframework.data.repository.CrudRepository;

public interface LancheDao extends CrudRepository<Lanche,Long> {
}
