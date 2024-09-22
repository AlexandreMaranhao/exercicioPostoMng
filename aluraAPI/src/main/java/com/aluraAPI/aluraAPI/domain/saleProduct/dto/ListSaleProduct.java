package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

public record ListSaleProduct(
        long id,
        float quantity,
        long saleId,
        long productId
) {
/*
    public DadosListagemVendaProduto(VendaProduto vendaProduto){
        this(vendaProduto.getId(),
                vendaProduto.getQuantidade(),
                vendaProduto.getVendasId(),
                vendaProduto.getProdutoId()
      );
    }

 */
}
