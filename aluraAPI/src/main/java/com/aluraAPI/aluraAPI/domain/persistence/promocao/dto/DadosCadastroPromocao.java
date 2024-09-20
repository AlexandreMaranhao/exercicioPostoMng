package com.aluraAPI.aluraAPI.domain.persistence.promocao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroPromocao(
        @NotBlank
        String nome,
        @NotNull
        Date validade
) {
}
