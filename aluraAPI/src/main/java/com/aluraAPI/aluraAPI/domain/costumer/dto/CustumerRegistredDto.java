package com.aluraAPI.aluraAPI.domain.costumer.dto;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;

public record CustumerRegistredDto(
        long id,
        String cpf,
        String name,
        Loyalty loyalty,
        boolean active
) {

}
