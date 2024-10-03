package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisterDto(

        LocalDateTime date,
        @NotNull

        Long paymentMethodId,

        long costumerId,

        long userId,

        long dealId,

        boolean refound
)
    {


    }
