package com.aluraAPI.aluraAPI.domain.paymentMethod.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePaymentMethod(
        @NotNull
        long id,

        String method,

        String discount


) {
}