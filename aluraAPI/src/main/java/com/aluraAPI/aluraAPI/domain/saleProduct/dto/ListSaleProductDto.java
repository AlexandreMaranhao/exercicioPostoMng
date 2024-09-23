package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;

public record ListSaleProductDto(
        long id,
        float quantity,
        long saleId,
        long productId
) {
/*
    public ListSaleProductDto(SaleProduct listSaleProductInput){
        this(listSaleProductInput.getId(),
                listSaleProductInput.getQuantity(),
                listSaleProductInput.getSale().getId(),
                listSaleProductInput.getProduct().getId()
      );
    }

*/
}
