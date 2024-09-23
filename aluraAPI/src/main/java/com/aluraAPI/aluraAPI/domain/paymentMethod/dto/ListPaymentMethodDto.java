package com.aluraAPI.aluraAPI.domain.paymentMethod.dto;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;

public record ListPaymentMethodDto(

        String method,

        String discount,

        boolean active
) {
    public ListPaymentMethodDto(PaymentMethod paymentMethodInput){
        this(paymentMethodInput.getMethod(),
                paymentMethodInput.getDiscount(),
                paymentMethodInput.isActive()
        );

    }
}
