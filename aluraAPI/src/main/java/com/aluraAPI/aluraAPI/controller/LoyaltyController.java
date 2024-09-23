package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.UpdateLoyaltyDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.RegisterLoyaltyDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.ListLoyaltyDto;
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
    public void cadastrarFidelidade(@RequestBody @Valid RegisterLoyaltyDto inputedData){
        loyaltyRepository.save(new Loyalty(inputedData));
    }

    @GetMapping
    public List<ListLoyaltyDto> listarFidelidade(){
        return loyaltyRepository.findAll().stream().map(ListLoyaltyDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarFidelidade(@RequestBody @Valid UpdateLoyaltyDto inputedData){
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
