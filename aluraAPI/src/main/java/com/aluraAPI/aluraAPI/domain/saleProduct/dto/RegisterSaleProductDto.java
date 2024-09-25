package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import com.aluraAPI.aluraAPI.domain.sale.Sale;
import jakarta.validation.constraints.NotNull;

public record RegisterSaleProductDto(
        @NotNull
        float quantity,
        @NotNull
        long saleId,
        @NotNull
        long productId
) {

}
