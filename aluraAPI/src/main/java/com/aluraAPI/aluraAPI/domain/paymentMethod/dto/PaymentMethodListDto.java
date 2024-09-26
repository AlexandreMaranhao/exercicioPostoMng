package com.aluraAPI.aluraAPI.domain.paymentMethod.dto;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;

public record PaymentMethodListDto(

        String method,

        String discount,

        boolean active
) {
    public PaymentMethodListDto(PaymentMethod paymentMethodInput){
        this(paymentMethodInput.getMethod(),
                paymentMethodInput.getDiscount(),
                paymentMethodInput.isActive()
        );

    }
}
