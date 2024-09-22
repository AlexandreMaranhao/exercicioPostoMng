package com.aluraAPI.aluraAPI.domain.paymentMethod.dto;

import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;

public record ListPaymentMethod(

        String method,

        String discount,

        boolean active
) {
    public ListPaymentMethod(PaymentMethod paymentMethodInput){
        this(paymentMethodInput.getMethod(),
                paymentMethodInput.getDiscount(),
                paymentMethodInput.isActive()
        );

    }
}
