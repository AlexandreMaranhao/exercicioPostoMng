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

        //costumerRepository.save(new Costumer(registerCostumerInput));//TODO: Resolver cliente igual ao produto
    }
/*
    @PostMapping("/completa")
    public ResponseEntity registerSale(@RequestBody @Valid SaleCompleteRegisterDto newSaleInput, UriComponentsBuilder uriBuilder)  {
        SaleRegisteredDetailsDto newCompleteSale = newSale.realizeCompleteSale(newSaleInput);
        //SaleCompleteReciptDTO receipt = newSale.generateRecipt(newCompleteSale, newSaleInput);
        //return ResponseEntity.ok("");
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(newCompleteSale.id()).toUri();
        return ResponseEntity.created(uri).body(newCompleteSale);
    }


   */

    @GetMapping
    public List<CostumerListDto> listCostumer(){
        return costumerRepository.findAll().stream().map(CostumerListDto::new).toList();
    }


    @PutMapping
    @Transactional
    public void updateCostumer(@RequestBody @Valid CostumerUpdateDto updateCostumerInput){ //TODO: Só atualiza Fidelidade
        var costumer = costumerRepository.getReferenceById(updateCostumerInput.id());
        costumer.updateCostumer(updateCostumerInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivateCostumer(@PathVariable Long id){
        var costumer = costumerRepository.getReferenceById(id);
        costumer.disable();
    }
}
