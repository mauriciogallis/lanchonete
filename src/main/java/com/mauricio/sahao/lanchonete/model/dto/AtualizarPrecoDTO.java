package com.mauricio.sahao.lanchonete.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AtualizarPrecoDTO {

    BigDecimal percentualAcrescimo;
    BigDecimal percentualDesconto;
    Long idIngrediente;

}
