package com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.dto;

import com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.MetodoPagamento;

public record DadosListagemMetodoPagamento(
        long id,
        String metodo
) {
    public DadosListagemMetodoPagamento(MetodoPagamento metodoPagamento) {
        this(metodoPagamento.getId(),
                metodoPagamento.getMetodo()
        );
    }

}