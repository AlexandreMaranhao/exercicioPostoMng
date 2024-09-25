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
    public RegistredSaleDetails(Sale sell) {
        this(sell.getId(),
                sell.getDate(),
                sell.getAmount(),
                sell.getInvoiceNumber(),
                sell.getPaymentMethodId().getId(),
                sell.getCostumerId().getId(),
                sell.getUserId().getId(),
                sell.getDealId().getId(),
                sell.isRefound());
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
