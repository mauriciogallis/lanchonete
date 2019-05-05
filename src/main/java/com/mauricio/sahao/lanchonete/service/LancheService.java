package com.mauricio.sahao.lanchonete.service;

import com.mauricio.sahao.lanchonete.model.Lanche;

import java.util.Optional;

public interface LancheService extends GenericService<Lanche,Long> {

    void save(Lanche lanche);

    Optional<Lanche> findByIdEager(Long id);
}
