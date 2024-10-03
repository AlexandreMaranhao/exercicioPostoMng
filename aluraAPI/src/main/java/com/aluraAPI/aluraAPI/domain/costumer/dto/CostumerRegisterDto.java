package com.aluraAPI.aluraAPI.domain.costumer.dto;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import jakarta.validation.constraints.NotBlank;

public record CostumerRegisterDto(
        @NotBlank
        String cpf,

        String name
)
    {
}
