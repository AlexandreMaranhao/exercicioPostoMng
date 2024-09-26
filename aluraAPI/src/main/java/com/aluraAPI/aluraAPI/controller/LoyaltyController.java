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
    public void cadastrarFidelidade(@RequestBody @Valid LoyaltyRegisterDto inputedData){
        loyaltyRepository.save(new Loyalty(inputedData));
    }

    @GetMapping
    public List<LoyaltyListDto> listarFidelidade(){
        return loyaltyRepository.findAll().stream().map(LoyaltyListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarFidelidade(@RequestBody @Valid LoyaltyUpdateDto inputedData){
        var fidelidade = loyaltyRepository.getReferenceById(inputedData.id());
        fidelidade.updateLoyalty(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirFidelidade(@PathVariable Long id){
        var fidelidade = loyaltyRepository.getReferenceById(id);
        fidelidade.disable();
    }
}
