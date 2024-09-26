package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyUpdateDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyRegisterDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fidelidade")
public class LoyaltyController {
    @Autowired
    private LoyaltyRepository loyaltyRepository;

    @PostMapping
    @Transactional
    public void registerLoyalty(@RequestBody @Valid LoyaltyRegisterDto registerLoyaltyInput){
        loyaltyRepository.save(new Loyalty(registerLoyaltyInput));
    }

    @GetMapping
    public List<LoyaltyListDto> listLoyalty(){
        return loyaltyRepository.findAll().stream().map(LoyaltyListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updateLoyalty(@RequestBody @Valid LoyaltyUpdateDto updateLoyaltyInput){
        var loyalty = loyaltyRepository.getReferenceById(updateLoyaltyInput.id());
        loyalty.updateLoyalty(updateLoyaltyInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactiveLoyalty(@PathVariable Long id){
        var loyalty = loyaltyRepository.getReferenceById(id);
        loyalty.disable();
    }
}
