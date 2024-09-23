package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record ListLoyaltyDto(
        long id,
        String number,
        int points
) {
    public ListLoyaltyDto(Loyalty loyaltyInput) {
        this(loyaltyInput.getId(),
                loyaltyInput.getNumber(),
                loyaltyInput.getPoints()
        );
    }
}
