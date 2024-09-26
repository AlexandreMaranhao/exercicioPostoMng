package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.costumer.CostumerRepository;

import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerUpdateDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerRegisterDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
public class CostumerController {
    @Autowired
    private CostumerRepository costumerRepository;

    @PostMapping
    @Transactional
    public void registerCostumer(@RequestBody @Valid CostumerRegisterDto registerCostumerInput){
        costumerRepository.save(new Costumer(registerCostumerInput));//TODO: Resolver cliente igual ao produto
    }

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
