package com.aluraAPI.aluraAPI.persistence.produto.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosAtualizarProduto(
        @NotNull
        Long id,

        String nome,

        float preco,

        long categoria_id

) {
}
