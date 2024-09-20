package com.aluraAPI.aluraAPI.domain.persistence.metodopagamento;

import com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.dto.DadosAtualizaMetodoPagamento;
import com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.dto.DadosCadastroMetodoPagamento;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "metodoPagamento")
@Entity(name = "MetodoPagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetodoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String metodo;
    private String desconto;
    private boolean ativo;


    public MetodoPagamento(@Valid DadosCadastroMetodoPagamento dados) {
        this.metodo = dados.metodo();
        this.ativo = true;
    }


    public void atualizarDadosMetodoPagamento(@Valid DadosAtualizaMetodoPagamento dados) {
        if (dados.metodo() != null){
            this.metodo = dados.metodo();
        }
    }

    public void inativar() {this.ativo = false;}



}
