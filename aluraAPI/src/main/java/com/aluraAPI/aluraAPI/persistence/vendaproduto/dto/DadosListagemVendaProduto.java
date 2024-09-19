package com.aluraAPI.aluraAPI.persistence.vendaproduto.dto;

import com.aluraAPI.aluraAPI.persistence.produto.Produto;
import com.aluraAPI.aluraAPI.persistence.vendaproduto.VendaProduto;

public record DadosListagemVendaProduto(
        long id,
        float quantidade,
        long vendas_id,
        long produto_id
) {

    public DadosListagemVendaProduto(VendaProduto vendaProduto){
        this(vendaProduto.getId(),
                vendaProduto.getQuantidade(),
                vendaProduto.getVendas_id(),
                vendaProduto.getProduto_id()
        );
    }
}
