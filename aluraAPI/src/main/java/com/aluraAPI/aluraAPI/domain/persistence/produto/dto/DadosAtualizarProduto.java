package com.aluraAPI.aluraAPI.domain.persistence.produto.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(
        @NotNull
        Long id,

        String nome,

        float preco,

        long categoriaId

) {
}
