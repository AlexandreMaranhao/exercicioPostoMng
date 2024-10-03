package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyUpdateDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyRegisterDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyListDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductDetailDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("fidelidade")
public class LoyaltyController {
    @Autowired
    private LoyaltyRepository loyaltyRepository;
    @Autowired
    private CostumerRepository costumerRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerLoyalty(@RequestBody @Valid LoyaltyRegisterDto registerLoyaltyInput, UriComponentsBuilder uriBuilder){
        Costumer costumerId = costumerRepository.findById(registerLoyaltyInput.costumerId()).get();
        Loyalty loyalty = new Loyalty(registerLoyaltyInput, costumerId);

        Loyalty response = loyaltyRepository.save(loyalty);

        var uri = uriBuilder.path("/fidelidade/{id}").buildAndExpand(loyalty.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
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
