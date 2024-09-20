package com.aluraAPI.aluraAPI.domain.persistence.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotBlank
        String nome,

        float preco,
        @NotNull //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        long categoriaId
)
    {
}
