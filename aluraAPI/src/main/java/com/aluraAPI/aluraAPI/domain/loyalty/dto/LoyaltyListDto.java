package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record LoyaltyListDto(
        long id,
        String number,
        int points
) {
    public LoyaltyListDto(Loyalty loyaltyInput) {
        this(loyaltyInput.getId(),
                loyaltyInput.getNumber(),
                loyaltyInput.getPoints()
        );
    }
}
