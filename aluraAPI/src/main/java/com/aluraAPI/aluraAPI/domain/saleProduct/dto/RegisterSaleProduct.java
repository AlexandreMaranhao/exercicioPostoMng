package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

import jakarta.validation.constraints.NotNull;

public record RegisterSaleProduct(
        @NotNull
        float quantity,
        @NotNull
        long saleId,
        @NotNull
        long productId
) {
}
