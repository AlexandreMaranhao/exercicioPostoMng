package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;

public record SaleProductListDto(
        long id,
        float quantity,
        long saleId,
        long productId
) {

    public SaleProductListDto(SaleProduct listSaleProductInput){
        this(listSaleProductInput.getId(),
                listSaleProductInput.getQuantity(),
                listSaleProductInput.getSaleId().getId(),
                listSaleProductInput.getProductId().getId()
      );
    }


}
