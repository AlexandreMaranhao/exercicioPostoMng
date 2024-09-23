package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record RegisterSaleDto(

        LocalDateTime date,
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
