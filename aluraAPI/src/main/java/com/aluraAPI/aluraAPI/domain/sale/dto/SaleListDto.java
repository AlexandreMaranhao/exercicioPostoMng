package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.user.User;

import java.time.LocalDateTime;

public record SaleListDto(
        long id,
        LocalDateTime date,
        Float amount,
        String invoiceNumber,
        Costumer costumerId,
        User userId,
        Deal dealId
)  {

}


