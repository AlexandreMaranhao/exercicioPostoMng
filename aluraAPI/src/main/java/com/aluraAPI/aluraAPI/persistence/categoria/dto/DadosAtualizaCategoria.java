package com.aluraAPI.aluraAPI.persistence.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCategoria(
        @NotNull
        Long id,
        @NotBlank
        String nome
) {

}
