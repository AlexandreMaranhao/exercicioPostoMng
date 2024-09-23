package com.aluraAPI.aluraAPI.domain.saleProduct.dto;

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
