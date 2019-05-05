package com.mauricio.sahao.lanchonete.model.enums;

public enum TipoPromocao {

    LIGHT("Light"),
    MUITA_CARNE("Muita carne"),
    MUITO_QUEIJO("Muito queijo"),
    NENHUMA("Nenhuma");

    private String descricao;

    TipoPromocao(String descricao){
        this.descricao = descricao;
    }

}
