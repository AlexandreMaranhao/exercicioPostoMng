package com.aluraAPI.aluraAPI.domain.costumer.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;

public record CostumerListDto(
        long id,
        String cpf,
        String name,
    //    long loyaltyId,
        boolean active
)
{

    public CostumerListDto(Costumer listCostumerInput){
        this(listCostumerInput.getId(),
                listCostumerInput.getCpf(),
                listCostumerInput.getName(),
      //          cliente.getFidelidade().getId(),//TODO: atualizar
                listCostumerInput.isActive()
        );
    }

}
