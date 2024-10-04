package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealUpdateDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealRegisterDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealListDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("promocao")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDeal(@RequestBody @Valid DealRegisterDto registerDealInput, UriComponentsBuilder uriBuilder){
        Deal deal = dealRepository.save(new Deal(registerDealInput));

        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(deal.getId()).toUri();

        return ResponseEntity.created(uri).body(deal);
    }

    @GetMapping
    public ResponseEntity<List<DealListDto>> listDeal(){
        var list = dealRepository.findAll().stream()
                .map(deal -> deal.castToDealListDto())
                .toList();

        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDeal(@RequestBody @Valid DealUpdateDto updateDealInput){
        Deal deal = dealRepository.getReferenceById(updateDealInput.id());
        deal.updateDeal(updateDealInput);

        return ResponseEntity.ok(new Deal(updateDealInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivateDeal(@PathVariable Long id){
        var deal = dealRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred deal with id: " + id));
        deal.disable();
        return ResponseEntity.noContent().build();
    }
}
