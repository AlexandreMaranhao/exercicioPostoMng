package com.aluraAPI.aluraAPI.persistence.metodopagamento.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMetodoPagamento(
        @NotBlank
        String nome
) {
}
