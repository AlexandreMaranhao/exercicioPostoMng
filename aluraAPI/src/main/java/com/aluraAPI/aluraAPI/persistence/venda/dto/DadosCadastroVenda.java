package com.aluraAPI.aluraAPI.persistence.venda.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroVenda(
        @NotNull
        Date data,
        @NotNull
        float  valor,

        String cupomfiscal,
        @NotNull //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int metodopagamento_id,
        //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int cliente_id,
        @NotNull //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int usuarios_id,
        //TODO: Adicionar @Valid quando adicionar a tabela e mudar para long
        int promocao_id,

        boolean extorno
)
    {
}
