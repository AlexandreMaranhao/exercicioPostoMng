package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.user.User;

import java.time.LocalDateTime;

public record ListSaleDto(
        long id,
        LocalDateTime date,
        float amount,
        String invoiceNumber,
        long costumerId,
        long userId,
        long dealId
)  {

    public ListSaleDto(Sale sale){
        this(sale.getId(),
                sale.getDate(),
                sale.getAmount(),
                sale.getInvoiceNumber(),
                sale.getCostumerId().getId(),
                sale.getUserId().getId(),
                sale.getDealId().getId());
    }
}


