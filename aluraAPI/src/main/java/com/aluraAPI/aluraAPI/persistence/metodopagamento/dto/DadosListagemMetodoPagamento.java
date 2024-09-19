package com.aluraAPI.aluraAPI.persistence.metodopagamento.dto;

import com.aluraAPI.aluraAPI.persistence.categoria.Categoria;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.MetodoPagamento;

public record DadosListagemMetodoPagamento(
        long id,
        String nome
) {
    public DadosListagemMetodoPagamento(MetodoPagamento metodoPagamento) {
        this(metodoPagamento.getId(),
                metodoPagamento.getNome()
        );
    }

}