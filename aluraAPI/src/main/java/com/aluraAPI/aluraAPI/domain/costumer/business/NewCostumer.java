package com.aluraAPI.aluraAPI.domain.costumer.business;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerRegisterDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CustumerRegistredDto;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyListDto;
import com.aluraAPI.aluraAPI.helper.CreateNew15CharNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NewCostumer {
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private LoyaltyRepository loyaltyRepository;


    public CustumerRegistredDto newRegisterCostumer(CostumerRegisterDto registerCostumerInput){
        Costumer newRegistredCostumer = new Costumer(registerCostumerInput);

        Loyalty loyalty = createLoyalty();

        Costumer costumer = costumerRepository.save(new Costumer(newRegistredCostumer, loyalty));


        return costumer.castToCustumerRegistredDto();
    }

    public Loyalty createLoyalty(){
        String loyaltyNumber = CreateNew15CharNumber.generateLoyaltyNumber();
        int points = 0;

        Loyalty loyalty = loyaltyRepository.save(new Loyalty(loyaltyNumber, points));

        return loyalty;
    }



}
