package com.aluraAPI.aluraAPI.persistence.metodopagamento;

import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosAtualizaCategoria;
import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosCadastroCategoria;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.dto.DadosAtualizaMetodoPagamento;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.dto.DadosCadastroMetodoPagamento;
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
    private String nome;
    private String desconto;
    private boolean ativo;


    public MetodoPagamento(@Valid DadosCadastroMetodoPagamento dados) {
        this.nome = dados.nome();
        this.ativo = true;
    }


    public void atualizarDadosMetodoPagamento(@Valid DadosAtualizaMetodoPagamento dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
    }

    public void inativar() {this.ativo = false;}



}
