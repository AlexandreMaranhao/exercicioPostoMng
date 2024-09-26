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
    public void cadastrarCliente(@RequestBody @Valid CostumerRegisterDto inputedData){
        costumerRepository.save(new Costumer(inputedData));//TODO: Resolver cliente igual ao produto
    }

    @GetMapping
    public List<CostumerListDto> listarCliente(){
        return costumerRepository.findAll().stream().map(CostumerListDto::new).toList();
    }


    @PutMapping
    @Transactional
    public void atualizarCliente(@RequestBody @Valid CostumerUpdateDto inputedData){ //TODO: Só atualiza Fidelidade
        var cliente = costumerRepository.getReferenceById(inputedData.id());
        cliente.updateCostumer(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirCliente(@PathVariable Long id){
        var cliente = costumerRepository.getReferenceById(id);
        cliente.disable();
    }
}
