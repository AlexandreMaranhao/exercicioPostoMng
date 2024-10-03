package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisteredDetailsDto(

        Long id,

        LocalDateTime date,
        @NotNull
        Float  amount,

        String invoiceNumber,
        @NotNull
        PaymentMethod paymentMethodId,

        Costumer costumer,
        @NotNull
        User user,

        Deal deal,

        boolean refound

) {
    public SaleRegisteredDetailsDto(Sale thisSale) {
        this(thisSale.getId(),
                thisSale.getDate(),
                thisSale.getAmount(),
                thisSale.getInvoiceNumber(),
                thisSale.getPaymentMethodId(),
                thisSale.getCostumerId(),
                thisSale.getUserId(),
                thisSale.getDealId(),
                thisSale.isRefound());
    }

    public SaleRegisteredDetailsDto(SaleRegisteredDetailsDto registeredSale) {
        this(registeredSale.id(),
                registeredSale.date(),
                registeredSale.amount(),
                registeredSale.invoiceNumber(),
                registeredSale.paymentMethodId(),
                registeredSale.costumer(),
                registeredSale.user(),
                registeredSale.deal(),
                registeredSale.refound());
    }
}
