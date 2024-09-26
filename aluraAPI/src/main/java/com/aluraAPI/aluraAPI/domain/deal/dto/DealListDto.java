package com.aluraAPI.aluraAPI.domain.deal.dto;

import com.aluraAPI.aluraAPI.domain.deal.Deal;

import java.util.Date;

public record DealListDto(
        Long id,
        String name,
        Date validity
) {
    public DealListDto(Deal dealInput) {
        this(dealInput.getId(),
                dealInput.getName(),
                dealInput.getValidity()
        );
    }
}
