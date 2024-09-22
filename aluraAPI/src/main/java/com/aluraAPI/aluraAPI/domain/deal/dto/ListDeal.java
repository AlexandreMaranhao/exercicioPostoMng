package com.aluraAPI.aluraAPI.domain.deal.dto;

import com.aluraAPI.aluraAPI.domain.deal.Deal;

import java.util.Date;

public record ListDeal(
        Long id,
        String name,
        Date validity
) {
    public ListDeal(Deal dealInput) {
        this(dealInput.getId(),
                dealInput.getName(),
                dealInput.getValidity()
        );
    }
}
