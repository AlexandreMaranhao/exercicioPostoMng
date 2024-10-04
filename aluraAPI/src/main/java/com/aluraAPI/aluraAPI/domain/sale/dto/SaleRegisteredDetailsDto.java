package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisteredDetailsDto(

        long id,

        LocalDateTime date,
        @NotNull
        float  amount,

        String invoiceNumber,

        PaymentMethod paymentMethod,

        Costumer costumer,

        User user,

        Deal deal,

        boolean refound

) {

}
