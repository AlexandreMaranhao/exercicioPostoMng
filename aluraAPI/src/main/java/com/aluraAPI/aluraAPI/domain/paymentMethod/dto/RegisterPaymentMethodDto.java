package com.aluraAPI.aluraAPI.domain.paymentMethod.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterPaymentMethodDto(
        @NotBlank
        String method,

        String discount
) {
}
