package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


public record RegisterCompleteSaleDto(
        LocalDateTime date,
        @NotNull
        float  amount,

        String invoiceNumber,

        long paymentMethodId,

        long costumerId,

        long userId,

        long dealId,

        boolean refound,

        List<RegisterSaleProductDto> products
) {




    public List<RegisterSaleProductDto> getProducts(){
        return products;
    }

    public Object getAmount() {
        return amount;
    }

    public Object getInvoiceNumber() {
        return invoiceNumber;
    }

    public Object getPaymentMethodId() {
        return paymentMethodId;
    }

    public Object getCostumerId() {
        return costumerId;
    }

    public Object getUserId() {
        return userId;
    }

    public Object getDealId() {
        return dealId;
    }
}
