package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.LoyaltyRepository;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.UpdateLoyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.RegisterLoyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.ListLoyalty;
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
    public void cadastrarFidelidade(@RequestBody @Valid RegisterLoyalty inputedData){
        loyaltyRepository.save(new Loyalty(inputedData));
    }

    @GetMapping
    public List<ListLoyalty> listarFidelidade(){
        return loyaltyRepository.findAll().stream().map(ListLoyalty::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarFidelidade(@RequestBody @Valid UpdateLoyalty inputedData){
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
