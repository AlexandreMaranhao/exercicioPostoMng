package com.aluraAPI.aluraAPI.domain.costumer.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record CostumerListDto(
        long id,
        String cpf,
        String name,
        Long loyaltyId,
        boolean active
)
{

    public CostumerListDto(Costumer listCostumerInput){
        this(listCostumerInput.getId(),
                listCostumerInput.getCpf(),
                listCostumerInput.getName(),
                listCostumerInput.getLoyaltyId().getId(),
                listCostumerInput.isActive()
        );
    }

}
