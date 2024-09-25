package com.aluraAPI.aluraAPI.domain.stock.dto;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.stock.Stock;

import java.time.LocalDateTime;

public record StockDetailDto(
        long id,
        float quantity,
        LocalDateTime validity,
        Product Product

) {
    public StockDetailDto(Stock stock){
        this(stock.getId(),
                stock.getQuantity(),
                stock.getValidity(),
                stock.getProductId()
        );

    }
}