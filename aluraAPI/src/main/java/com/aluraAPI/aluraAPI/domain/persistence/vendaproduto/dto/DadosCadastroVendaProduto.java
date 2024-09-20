package com.aluraAPI.aluraAPI.domain.persistence.vendaproduto.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroVendaProduto(
        @NotNull
        float quantidade,
        @NotNull
        long vendasId,
        @NotNull
        long produtoId
) {
}
