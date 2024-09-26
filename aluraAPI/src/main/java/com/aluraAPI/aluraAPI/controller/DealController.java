package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealUpdateDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealRegisterDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("promocao")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @PostMapping
    @Transactional
    public void registerDeal(@RequestBody @Valid DealRegisterDto registerDealInput){
        dealRepository.save(new Deal(registerDealInput));
    }

    @GetMapping
    public List<DealListDto> listDeal(){

        return dealRepository.findAll().stream().map(DealListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updateDeal(@RequestBody @Valid DealUpdateDto updateDealInput){
        var deal = dealRepository.getReferenceById(updateDealInput.id());
        deal.updateDeal(updateDealInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivateDeal(@PathVariable Long id){
        var deal = dealRepository.getReferenceById(id);
        deal.disable();
    }
}
