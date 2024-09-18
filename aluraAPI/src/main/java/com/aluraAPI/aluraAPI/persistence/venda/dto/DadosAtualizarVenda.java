package com.aluraAPI.aluraAPI.persistence.venda.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarVenda(
        @NotNull
        long id,

        String cupomfiscal,

        int metodopagamento_id,

        int cliente_id
) {
}
