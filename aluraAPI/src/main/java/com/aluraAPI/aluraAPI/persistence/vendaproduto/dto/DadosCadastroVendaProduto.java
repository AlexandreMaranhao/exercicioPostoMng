package com.aluraAPI.aluraAPI.persistence.vendaproduto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroVendaProduto(
        @NotNull
        float quantidade,
        @NotNull
        long  vendas_id,
        @NotNull
        long produto_id
) {
}
