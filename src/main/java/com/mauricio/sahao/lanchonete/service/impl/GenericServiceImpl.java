package com.mauricio.sahao.lanchonete.service.impl;

import com.mauricio.sahao.lanchonete.service.GenericService;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class GenericServiceImpl<T,ID> implements GenericService<T,ID> {

    protected abstract CrudRepository<T,ID> getDao();

    public void save(T ob) {
        getDao().save(ob);
    }

    public Optional findById(ID id) {
        return getDao().findById(id);
    }

    public boolean existsById(ID var1){
        return getDao().existsById(var1);
    }


    public Iterable<T> findAll(){
        return getDao().findAll();
    }

    public Iterable<T> findAllById(Iterable<ID> var1){
        return getDao().findAllById(var1);
    }

    public long count(){
        return getDao().count();
    }

    public void deleteById(ID var1){
        getDao().deleteById(var1);
    }

    public void delete(T var1){
        delete(var1);
    }

    public void deleteAll(Iterable<? extends T> var1){
        getDao().deleteAll(var1);
    }

    public void deleteAll(){
        getDao().deleteAll();
    }

}
