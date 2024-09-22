package com.aluraAPI.aluraAPI.domain.costumer.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;

public record ListCostumer(
        long id,
        String cpf,
        String name,
    //    long loyaltyId,
        boolean active
)
{

    public ListCostumer(Costumer costumerInput){
        this(costumerInput.getId(),
                costumerInput.getCpf(),
                costumerInput.getName(),
      //          cliente.getFidelidade().getId(),//TODO: atualizar
                costumerInput.isActive()
        );
    }

}
