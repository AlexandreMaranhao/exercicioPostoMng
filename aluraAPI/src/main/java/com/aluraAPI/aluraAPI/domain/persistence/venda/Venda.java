package com.aluraAPI.aluraAPI.domain.persistence.venda;

import com.aluraAPI.aluraAPI.domain.persistence.venda.dto.DadosAtualizarVenda;
import com.aluraAPI.aluraAPI.domain.persistence.venda.dto.DadosCadastroVenda;
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
    private int metodopagamentoId;
    private int clienteId;
    private int usuariosId;
    private int promocaoId;
    private boolean extorno;

    public Venda(DadosCadastroVenda dados){
        this.data = dados.data();
        this.valor = dados.valor();
        this.cupomfiscal = dados.cupomfiscal();
        this.metodopagamentoId = dados.metodopagamentoId();
        this.clienteId = dados.clienteId();
        this.usuariosId = dados.usuariosId();
        this.promocaoId = dados.promocaoId();
        this.extorno = dados.extorno();

    }

    public void atualizarDadosVenda(@Valid DadosAtualizarVenda dados){
        if (dados.cupomfiscal() != null){
            this.cupomfiscal = dados.cupomfiscal();
        }
        if (dados.metodopagamentoId() != 0.0d){
            this.metodopagamentoId = dados.metodopagamentoId();
        }
        if (dados.clienteId() != 0.0d){
            this.clienteId = dados.clienteId();
        }

    }

}
