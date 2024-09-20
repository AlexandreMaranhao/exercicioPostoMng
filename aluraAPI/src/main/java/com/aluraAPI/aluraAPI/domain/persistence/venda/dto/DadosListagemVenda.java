package com.aluraAPI.aluraAPI.domain.persistence.venda.dto;

import com.aluraAPI.aluraAPI.domain.persistence.venda.Venda;

import java.util.Date;

public record DadosListagemVenda(
        long id,
        Date data,
        float valor,
        String cupomfiscal,
        int clienteId,
        int usuariosId,
        int promocaoId
) {

    public DadosListagemVenda(Venda venda){
        this(venda.getId(),
                venda.getData(),
                venda.getValor(),
                venda.getCupomfiscal(),
                venda.getClienteId(),
                venda.getUsuariosId(),
                venda.getPromocaoId());
    }
}


