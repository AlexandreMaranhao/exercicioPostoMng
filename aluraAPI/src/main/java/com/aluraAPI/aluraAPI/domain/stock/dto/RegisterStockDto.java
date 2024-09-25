package com.aluraAPI.aluraAPI.domain.stock.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisterStockDto(
        @NotNull
        float quantity,
        @NotNull
        LocalDateTime validity,
        @NotNull
        Long productId

) {


    public RegisterStockDto(Stock stock) {
        this(stock.getQuantity(),
                stock.getValidity(),
                stock.getProductId().getId()
        );

    }
}
