package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegisterSaleDto(
        @NotNull
        Date date,
        @NotNull
        float  amount,

        String invoiceNumber,

        long paymentMethodId,

        long costumerId,

        long userId,

        long dealId,

        boolean refound
)
    {


    }
