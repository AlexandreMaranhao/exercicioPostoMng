package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto;
import com.aluraAPI.aluraAPI.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record SaleReceiptDto(
        long id,
        LocalDateTime date,
        Float amount,
        String invoiceNumber,
        PaymentMethod paymentMethod,
        Costumer costumerId,
        User userId,
        Deal dealId,
        boolean refound,
        Integer loyaltyPointsUsed,
        Integer LoyaltyPointsGenerated,
        List<SaleProductRegisterDto> products
) {

}
