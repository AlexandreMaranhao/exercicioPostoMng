package com.aluraAPI.aluraAPI.domain.persistence.categoria.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(
        @NotBlank
        String nome
) {

}
