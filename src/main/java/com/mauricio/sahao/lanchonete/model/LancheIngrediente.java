package com.mauricio.sahao.lanchonete.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="lanche_ingrediente")
@Data
@EnableAutoConfiguration
public class LancheIngrediente implements Serializable {

    private static final long serialVersionUID = -8310663139228968502L;
    private static final String SEQUENCE = "seq_id_lanche_ingrediente";

    @Id
    @SequenceGenerator(name=SEQUENCE, sequenceName=SEQUENCE, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=SEQUENCE)
    @Column(name = "id", updatable=false)
    private Long id;

    private BigDecimal quantidade;

    @Column(name="id_lanche")
    private Long idLanche;

    @Column(name="id_ingrediente")
    private Long idIngrediente;

    @Transient
    private Ingrediente ingrediente;

}
