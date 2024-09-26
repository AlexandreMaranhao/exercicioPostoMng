package com.aluraAPI.aluraAPI.domain.stock.dto;

import com.aluraAPI.aluraAPI.domain.stock.Stock;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StockRegisterDto(

        @NotNull
        float quantity,

        @NotNull
        @Future
        LocalDateTime validity,

        @NotNull
        Long productId

) {


    public StockRegisterDto(Stock stock) {
        this(stock.getQuantity(),
                stock.getValidity(),
                stock.getProductId().getId()
        );

    }
}
