package com.aluraAPI.aluraAPI.domain.sale.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record SaleCompleteReciptDTO(
        Long id,
        LocalDateTime date,
        Float amount,
        String invoiceNumber,
        PaymentMethod paymentMethod,
        Costumer costumer,
        User user,
        Deal deal,
        boolean refound,
        List<com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto> products,
        Float finalAmount,
        Integer loyaltyPointsUsed,
        Integer loyaltyPointsGenerated

) {

    public SaleCompleteReciptDTO(SaleRegisteredDetailsDto newSavedCompleteSale,
                                 SaleCompleteRegisterDto newSaleInput,
                                 Float totalAmount,
                                 Integer loyaltyPoints,
                                 Integer loyaltyPointsGenerated)
    {
        this(newSavedCompleteSale.id(),
                newSavedCompleteSale.date(),
                newSavedCompleteSale.amount(),
                newSavedCompleteSale.invoiceNumber(),
                newSavedCompleteSale.paymentMethodId(),
                newSavedCompleteSale.costumer(),
                newSavedCompleteSale.user(),
                newSavedCompleteSale.deal(),
                newSavedCompleteSale.refound(),
                newSaleInput.products(),
                totalAmount,
                loyaltyPoints,
                loyaltyPointsGenerated

        );


    }

    public SaleCompleteReciptDTO(Object o,
                                 Object o1,
                                 SaleRegisteredDetailsDto newSavedCompleteSale,
                                 Object o2,
                                 Object o3,
                                 SaleCompleteRegisterDto newSaleInput,
                                 Object o4,
                                 Object o5,
                                 Float totalAmount,
                                 Object o6,
                                 Object o7,
                                 Integer loyaltyPoints,
                                 Object o8,
                                 Object o9,
                                 Integer loyaltyPointsGenerated) {
        this(newSavedCompleteSale.id(),
                newSavedCompleteSale.date(),
                newSavedCompleteSale.amount(),
                newSavedCompleteSale.invoiceNumber(),
                newSavedCompleteSale.paymentMethodId(),
                newSavedCompleteSale.costumer(),
                newSavedCompleteSale.user(),
                newSavedCompleteSale.deal(),
                newSavedCompleteSale.refound(),
                newSaleInput.products(),
                totalAmount,
                loyaltyPoints,
                loyaltyPointsGenerated

        );
    }
}
