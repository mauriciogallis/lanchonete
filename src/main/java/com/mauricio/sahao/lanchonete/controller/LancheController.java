package com.mauricio.sahao.lanchonete.controller;

import com.mauricio.sahao.lanchonete.model.Lanche;
import com.mauricio.sahao.lanchonete.service.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lanche")
public class LancheController {

    private final LancheService service;

    @Autowired
    public LancheController(LancheService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    private Lanche buscar(@PathVariable("id") Long id){
        return service.findByIdEager(id).orElse(null);
    }

    @PostMapping("/inserir")
    @ResponseBody
    private Lanche inserir(@RequestBody Lanche lanche){
        service.save(lanche);
        return service.findByIdEager(lanche.getId()).orElse(null);
    }

    @PutMapping("/atualizar")
    @ResponseBody
    private Lanche atualizar(@RequestBody Lanche lanche){
        service.save(lanche);
        return service.findByIdEager(lanche.getId()).orElse(null);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    private String delete(@PathVariable("id") Long id){
        service.deleteById(id);
        return "Lanche: "+id+" excluído com sucesso";
    }



}
