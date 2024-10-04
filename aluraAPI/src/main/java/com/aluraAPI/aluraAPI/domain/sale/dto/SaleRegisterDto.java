package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisterDto(

        LocalDateTime date,
        @NotNull

        Long paymentMethodId,

        Long costumerId,

        Long userId,

        Long dealId,

        boolean refound,

        Integer loyaltyPoints

)
    {


    }
