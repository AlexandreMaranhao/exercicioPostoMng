package com.aluraAPI.aluraAPI.persistence.venda.dto;

import com.aluraAPI.aluraAPI.persistence.venda.Venda;

import java.util.Date;

public record DadosListagemVenda(
        long id,
        Date data,
        float valor,
        String cupomfiscal,
        int cliente_id,
        int usuarios_id,
        int promocao_id
) {

    public DadosListagemVenda(Venda venda){
        this(venda.getId(), venda.getData(), venda.getValor(), venda.getCupomfiscal(), venda.getCliente_id(),
                venda.getUsuarios_id(), venda.getPromocao_id());
    }
}


