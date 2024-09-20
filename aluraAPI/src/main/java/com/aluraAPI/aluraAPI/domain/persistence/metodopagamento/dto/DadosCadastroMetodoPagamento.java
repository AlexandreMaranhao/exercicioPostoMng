package com.aluraAPI.aluraAPI.domain.persistence.metodopagamento.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMetodoPagamento(
        @NotBlank
        String metodo
) {
}
