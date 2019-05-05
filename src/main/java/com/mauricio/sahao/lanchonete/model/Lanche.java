package com.mauricio.sahao.lanchonete.model;

import com.mauricio.sahao.lanchonete.model.enums.TipoIngrediente;
import com.mauricio.sahao.lanchonete.model.enums.TipoPromocao;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="lanche")
@EnableAutoConfiguration
public class Lanche implements Serializable {

    private static final long serialVersionUID = 2788517812187250292L;
    private static final String SEQUENCE = "seq_id_lanche";

    @Id
    @SequenceGenerator(name=SEQUENCE, sequenceName=SEQUENCE, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=SEQUENCE)
    @Column(name = "id", updatable=false)
    private Long id;

    private String nome;

    @Transient
    private BigDecimal preco;

    @Transient
    private List<TipoPromocao> tipoPromocao = new ArrayList<>();

    @OneToMany(mappedBy = "idLanche")
    private List<LancheIngrediente> ingredientes;

    public BigDecimal getPreco(){
        BigDecimal preco = BigDecimal.ZERO;
        preco = getPrecoPromocaoQuantidade(preco);
        preco = getPrecoPromocaoLight(preco);
        verificaInexistenciaPromocao();
        return preco;
    }

    private void verificaInexistenciaPromocao() {
        if(tipoPromocao.size() == 0){
            tipoPromocao.add(TipoPromocao.NENHUMA);
        }
    }

    private BigDecimal getPrecoPromocaoQuantidade(BigDecimal preco) {
        for (LancheIngrediente l : this.getIngredientes()){
            preco = preco.add(getQuantidadePromocaoAcumulativa(l).multiply(l.getIngrediente().getPreco()));
        }
        return preco;
    }

    private BigDecimal getQuantidadePromocaoAcumulativa(LancheIngrediente l) {
        if(enquadraPromocaoQuantidade(l)){
            tipoPromocao.add(l.getIngrediente().getTipoIngrediente().equals(TipoIngrediente.HAMBURGUER)?
                    TipoPromocao.MUITA_CARNE:TipoPromocao.MUITO_QUEIJO);

            BigDecimal quantidadeCorrigida = l.getQuantidade().divide(new BigDecimal(3),1,RoundingMode.CEILING);

            return l.getQuantidade().subtract(quantidadeCorrigida);
        }
        return l.getQuantidade();
    }

    private boolean enquadraPromocaoQuantidade(LancheIngrediente l) {
        return l.getQuantidade().compareTo(new BigDecimal(3)) >= 0
                && (l.getIngrediente().getTipoIngrediente().equals(TipoIngrediente.HAMBURGUER)||
                l.getIngrediente().getTipoIngrediente().equals(TipoIngrediente.QUEIJO));
    }

    private BigDecimal getPrecoPromocaoLight(BigDecimal preco){
        TipoPromocao tipo = verificaIngrediente(TipoIngrediente.ALFACE)
                && !verificaIngrediente(TipoIngrediente.BACON) ? TipoPromocao.LIGHT : TipoPromocao.NENHUMA;

        if(tipo.equals(TipoPromocao.LIGHT)) {
            preco = preco.subtract(preco.divide(new BigDecimal(100),2, RoundingMode.CEILING).multiply(new BigDecimal(10)));
            tipoPromocao.add(TipoPromocao.LIGHT);
        }
        return preco;
    }

    private boolean verificaIngrediente(TipoIngrediente tipo) {
        return this.getIngredientes().stream().map(LancheIngrediente::getIngrediente).
                anyMatch(s -> s.getTipoIngrediente().equals(tipo));
    }

}
