package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

public record SaleUpdateDto(
        @NotNull
        long id,

        String invoiceNumber,

        long paymentMethodId,

        long costumerId
) {
}
