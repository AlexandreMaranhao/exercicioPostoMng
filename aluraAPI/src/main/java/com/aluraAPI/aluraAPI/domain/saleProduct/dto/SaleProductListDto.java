package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;

public record SaleProductListDto(
        long id,
        float quantity,
        long saleId,
        long productId
) {

    public SaleProductListDto(SaleProduct saleProduct){
        this(saleProduct.getId(),
                saleProduct.getQuantity(),
                saleProduct.getSaleId().getId(),
                saleProduct.getProductId().getId()
      );
    }


}
