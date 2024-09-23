package com.aluraAPI.aluraAPI.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateSaleDto(
        @NotNull
        long id,

        String invoiceNumber,

        long paymentMethodId,

        long costumerId
) {
}
