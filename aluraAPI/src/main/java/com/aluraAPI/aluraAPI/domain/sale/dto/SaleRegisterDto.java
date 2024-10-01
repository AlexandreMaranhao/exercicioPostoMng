package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisterDto(

        LocalDateTime date,
        @NotNull

        long paymentMethodId,

        long costumerId,

        long userId,

        long dealId,

        boolean refound
)
    {


    }
