package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.deal.DealRepository;
import com.aluraAPI.aluraAPI.domain.deal.dto.UpdateDealDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.RegisterDealDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.ListDealDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("promocao")
public class dealController {

    @Autowired
    private DealRepository dealRepository;

    @PostMapping
    @Transactional
    public void cadastrarPromocao(@RequestBody @Valid RegisterDealDto inputedData){
        dealRepository.save(new Deal(inputedData));
    }

    @GetMapping
    public List<ListDealDto> listarPromocao(){

        return dealRepository.findAll().stream().map(ListDealDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarPromocao(@RequestBody @Valid UpdateDealDto inputedData){
        var promocao = dealRepository.getReferenceById(inputedData.id());
        promocao.updateDeal(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirPromocao(@PathVariable Long id){
        var promocao = dealRepository.getReferenceById(id);
        promocao.disable();
    }
}
