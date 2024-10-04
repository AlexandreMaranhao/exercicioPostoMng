package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;

import com.aluraAPI.aluraAPI.domain.costumer.business.NewCostumer;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerUpdateDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerRegisterDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerListDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CustumerRegistredDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleCompleteRegisterDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisteredDetailsDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class CostumerController {
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private NewCostumer newCostumer;


    @PostMapping
    @Transactional
    public ResponseEntity registerCostumer(@RequestBody @Valid CostumerRegisterDto registerCostumerInput, UriComponentsBuilder uriBuilder){
        CustumerRegistredDto costumer = newCostumer.newRegisterCostumer(registerCostumerInput);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(costumer.id()).toUri();

        return ResponseEntity.created(uri).body(costumer);
    }


    @GetMapping
    public ResponseEntity<List<CostumerListDto>> listCostumer(){
        var list = costumerRepository.findAll().stream()
                .map(costumer -> costumer.castToCostumerListDto())
                .toList();

        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCostumer(@RequestBody @Valid CostumerUpdateDto updateCostumerInput){
        Costumer costumer = costumerRepository.getReferenceById(updateCostumerInput.id());
        costumer.updateCostumer(updateCostumerInput);

        return ResponseEntity.ok(new Costumer(updateCostumerInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivateCostumer(@PathVariable Long id){
        var costumer = costumerRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred costumer with id: " + id));
        costumer.disable();
        return ResponseEntity.noContent().build();
    }
}
