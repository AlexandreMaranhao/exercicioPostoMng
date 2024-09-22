package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record ListLoyalty(
        long id,
        String number,
        int points
) {
    public ListLoyalty(Loyalty loyaltyInput) {
        this(loyaltyInput.getId(),
                loyaltyInput.getNumber(),
                loyaltyInput.getPoints()
        );
    }
}
