package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

public record SaleUpdateDto(
        @NotNull
        Long id,

        String invoiceNumber,

        Long paymentMethodId,

        Long costumerId
) {
}
