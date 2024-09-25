package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.sale.Sale;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistredSaleDetails(

        long id,

        LocalDateTime date,
        @NotNull
        float  amount,

        String invoiceNumber,

        long paymentMethodId,

        long costumerId,

        long userId,

        long dealId,

        boolean refound
) {
    public RegistredSaleDetails(Sale thisSale) {
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

    public RegistredSaleDetails(RegistredSaleDetails registeredSale) {
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
