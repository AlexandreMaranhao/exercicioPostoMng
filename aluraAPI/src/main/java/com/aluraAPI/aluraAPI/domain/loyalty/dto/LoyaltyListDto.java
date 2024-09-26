package com.aluraAPI.aluraAPI.domain.loyalty.dto;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record LoyaltyListDto(
        long id,
        String number,
        int points
) {
    public LoyaltyListDto(Loyalty listLoyaltyInput) {
        this(listLoyaltyInput.getId(),
                listLoyaltyInput.getNumber(),
                listLoyaltyInput.getPoints()
        );
    }
}
