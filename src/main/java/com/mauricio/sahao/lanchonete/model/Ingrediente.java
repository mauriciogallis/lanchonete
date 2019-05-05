package com.mauricio.sahao.lanchonete.model;

import com.mauricio.sahao.lanchonete.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="ingrediente")
@EnableAutoConfiguration
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = -7294752633325971738L;
    private static final String SEQUENCE = "seq_id_ingrediente";

    @Id
    @SequenceGenerator(name=SEQUENCE, sequenceName=SEQUENCE, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=SEQUENCE)
    @Column(name = "id", updatable=false)
    private Long id;

    private String nome;

    private BigDecimal preco;

    private TipoIngrediente tipoIngrediente;

}
