package com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMetodoPagamento(
        @NotNull
        Long id,
        @NotBlank
        String metodo
) {
}
