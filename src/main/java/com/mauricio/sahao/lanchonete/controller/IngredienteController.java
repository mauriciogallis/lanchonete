package com.mauricio.sahao.lanchonete.controller;


import com.mauricio.sahao.lanchonete.model.Ingrediente;
import com.mauricio.sahao.lanchonete.model.dto.AtualizarPrecoDTO;
import com.mauricio.sahao.lanchonete.service.IngredienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingrediente")
@Api("Ingrediente Controller")
public class IngredienteController {

    private final IngredienteService service;

    @Autowired
    public IngredienteController(IngredienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation("Buscar ingrediente")
    private Ingrediente buscar(@PathVariable("id") Long id){
        return service.findById(id).orElse(null);
    }

    @PostMapping("/inserir")
    @ResponseBody
    @ApiOperation("Inserir ingrediente")
    private Ingrediente inserir(@RequestBody Ingrediente ingrediente){
        service.save(ingrediente);
        return ingrediente;
    }

    @PutMapping("/atualizar")
    @ResponseBody
    @ApiOperation("Atualizar ingrediente")
    private Ingrediente atualizar(@RequestBody Ingrediente ingrediente){
        service.save(ingrediente);
        return ingrediente;
    }

    @PutMapping("/atualizarPreco")
    @ResponseBody
    @ApiOperation(value="Atualizar Preco",
            notes = "Informar campo percentualAcrescimo para acréscimo ou percentualDesconto para desconto.\n" +
                    "Informar campo idIngrediente para atualizar um ingrediente específico, caso contrário será atualizado todos os ingredientes.")
    private String atualizar(@RequestBody AtualizarPrecoDTO atualizarPrecoDTO){
        service.atualizarPreco(atualizarPrecoDTO);
        return "Preco atualizado com sucesso.";
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    @ApiOperation("Deletar ingrediente")
    private String delete(@PathVariable("id") Long id){
        service.deleteById(id);
        return "Ingrediente: "+id+" excluído com sucesso";
    }

    @PostMapping("/incluirTodos")
    @ResponseBody
    @ApiOperation(value="Incluir todos ingredientes",
            notes = "Método desenvolvido para facilitar a inserção dos ingredientes, ele só executa se não houver registro na tabela de ingrediente.\n" +
                    "Este método retorna todos os ingredientes.")
    private Iterable<Ingrediente> incluirTodos(){
        return service.incluirIngredientes();
    }
}
