package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;

public record ListSaleProductDto(
        long id,
        float quantity,
        long saleId,
        long productId
) {

    public ListSaleProductDto(SaleProduct saleProduct){
        this(saleProduct.getId(),
                saleProduct.getQuantity(),
                saleProduct.getSaleId().getId(),
                saleProduct.getProductId().getId()
      );
    }


}
