package com.aluraAPI.aluraAPI.persistence.venda;

import com.aluraAPI.aluraAPI.persistence.venda.dto.DadosAtualizarVenda;
import com.aluraAPI.aluraAPI.persistence.venda.dto.DadosCadastroVenda;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Table(name = "venda")
@Entity(name ="venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date data;
    private float  valor;
    private String cupomfiscal;
    //TODO: mudar todas as FK para long
    private int metodopagamento_id;
    private int cliente_id;
    private int usuarios_id;
    private int promocao_id;
    private boolean extorno;

    public Venda(DadosCadastroVenda dados){
        this.data = dados.data();
        this.valor = dados.valor();
        this.cupomfiscal = dados.cupomfiscal();
        this.metodopagamento_id = dados.metodopagamento_id();
        this.cliente_id = dados.cliente_id();
        this.usuarios_id = dados.usuarios_id();
        this.promocao_id = dados.promocao_id();
        this.extorno = dados.extorno();

    }

    public void atualizarDadosVenda(@Valid DadosAtualizarVenda dados){
        if (cupomfiscal != null){
            this.cupomfiscal = dados.cupomfiscal();
        }
        if (metodopagamento_id != 0.0d){
            this.metodopagamento_id = dados.metodopagamento_id();
        }
        if (cliente_id != 0.0d){
            this.cliente_id = dados.cliente_id();
        }

    }

}
