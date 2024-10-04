package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SaleRegisteredDetailsDto(

        long id,

        LocalDateTime date,
        @NotNull
        float  amount,

        String invoiceNumber,

        PaymentMethod paymentMethodId,

        Costumer costumerId,

        User userId,

        Deal dealId,

        boolean refound

) {
    /*
    public SaleRegisteredDetailsDto(Sale thisSale) {
        this(thisSale.getId(),
                thisSale.getDate(),
                thisSale.getAmount(),
                thisSale.getInvoiceNumber(),
                thisSale.getPaymentMethodId().getId(),
                thisSale.getCostumerId().getId(),
                thisSale.getUserId().getId(),
                thisSale.getDealId().getId(),
                thisSale.isRefound());
    }


     */
    public SaleRegisteredDetailsDto(SaleRegisteredDetailsDto registeredSale) {
        this(registeredSale.id(),
                registeredSale.date(),
                registeredSale.amount(),
                registeredSale.invoiceNumber(),
                registeredSale.paymentMethodId(),
                registeredSale.costumerId(),
                registeredSale.userId(),
                registeredSale.dealId(),
                registeredSale.refound());
    }
}
