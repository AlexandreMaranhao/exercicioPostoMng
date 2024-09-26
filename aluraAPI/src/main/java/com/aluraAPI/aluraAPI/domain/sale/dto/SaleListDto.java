package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.sale.Sale;

import java.time.LocalDateTime;

public record SaleListDto(
        long id,
        LocalDateTime date,
        float amount,
        String invoiceNumber,
        long costumerId,
        long userId,
        long dealId
)  {

    public SaleListDto(Sale sale){
        this(sale.getId(),
                sale.getDate(),
                sale.getAmount(),
                sale.getInvoiceNumber(),
                sale.getCostumerId().getId(),
                sale.getUserId().getId(),
                sale.getDealId().getId());
    }
}


