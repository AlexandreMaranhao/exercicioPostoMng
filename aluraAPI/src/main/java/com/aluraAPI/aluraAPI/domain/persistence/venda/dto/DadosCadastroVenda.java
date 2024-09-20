package com.aluraAPI.aluraAPI.domain.persistence.venda.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVenda(
        @NotNull
        Date data,
        @NotNull
        float  valor,

        String cupomfiscal,
        @NotNull //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int metodopagamentoId,
        //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int clienteId,
        @NotNull //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int usuariosId,
        //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int promocaoId,

        boolean extorno
)
    {
}
