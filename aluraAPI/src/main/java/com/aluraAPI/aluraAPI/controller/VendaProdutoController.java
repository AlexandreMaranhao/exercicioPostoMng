package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.persistence.vendaproduto.VendaProduto;
import com.aluraAPI.aluraAPI.persistence.vendaproduto.VendaProdutoRepository;
import com.aluraAPI.aluraAPI.persistence.vendaproduto.dto.DadosCadastroVendaProduto;
import com.aluraAPI.aluraAPI.persistence.vendaproduto.dto.DadosListagemVendaProduto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendaproduto")
public class VendaProdutoController {

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;

    @PostMapping
    @Transactional
    public void cadastrarVendaProduto(@RequestBody @Valid DadosCadastroVendaProduto dados){
        vendaProdutoRepository.save(new VendaProduto(dados));
    }

    @GetMapping
    public List<DadosListagemVendaProduto> listarVendaProduto(){
        return vendaProdutoRepository.findAll().stream().map(DadosListagemVendaProduto::new).toList();
    }
}
