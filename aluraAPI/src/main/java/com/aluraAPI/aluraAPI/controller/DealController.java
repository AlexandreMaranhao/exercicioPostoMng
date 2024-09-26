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
    public void cadastrarPromocao(@RequestBody @Valid DealRegisterDto inputedData){
        dealRepository.save(new Deal(inputedData));
    }

    @GetMapping
    public List<DealListDto> listarPromocao(){

        return dealRepository.findAll().stream().map(DealListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarPromocao(@RequestBody @Valid DealUpdateDto inputedData){
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
