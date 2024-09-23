package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.sale.Sale;

import java.util.Date;

public record ListSaleDto(
        long id,
        Date date,
        float amount,
        String invoiceNumber,
        long costumerId,
        long userId,
        long dealId
) {

    public ListSaleDto(Sale venda){
        this(venda.getId(),
                venda.getDate(),
                venda.getAmount(),
                venda.getInvoiceNumber(),
                venda.getCostumerId().getId(),
                venda.getUserId().getId(),
                venda.getDealId().getId());
    }
}


