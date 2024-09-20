package com.aluraAPI.aluraAPI.domain.persistence.venda.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVenda(
        @NotNull
        long id,

        String cupomfiscal,

        int metodopagamentoId,

        int clienteId
) {
}
