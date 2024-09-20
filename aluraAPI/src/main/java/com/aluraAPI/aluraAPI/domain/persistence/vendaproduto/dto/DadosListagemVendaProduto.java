package com.aluraAPI.aluraAPI.domain.persistence.vendaproduto.dto;

import com.aluraAPI.aluraAPI.domain.persistence.vendaproduto.VendaProduto;

public record DadosListagemVendaProduto(
        long id,
        float quantidade,
        long vendasId,
        long produtoId
) {

    public DadosListagemVendaProduto(VendaProduto vendaProduto){
        this(vendaProduto.getId(),
                vendaProduto.getQuantidade(),
                vendaProduto.getVendasId(),
                vendaProduto.getProdutoId()
        );
    }
}
