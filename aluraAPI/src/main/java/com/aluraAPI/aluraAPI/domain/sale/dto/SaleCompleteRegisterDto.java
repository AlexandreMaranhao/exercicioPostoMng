package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;


public record SaleCompleteRegisterDto(

        LocalDateTime date,

        @NotNull
        long paymentMethodId,

        Long costumerId,
        @NotNull
        Long userId,

        Long dealId,

        boolean refound,

        Integer loyaltyPoints,

        List<SaleProductRegisterDto> products
) {

    public List<SaleProductRegisterDto> getProducts(){
        return products;
    }

    public Object getUserId() {
        return userId;
    }

}
