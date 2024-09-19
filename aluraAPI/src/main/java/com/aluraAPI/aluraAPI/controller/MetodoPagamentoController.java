package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.persistence.metodopagamento.MetodoPagamento;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.MetodoPagamentoRepository;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.dto.DadosAtualizaMetodoPagamento;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.dto.DadosCadastroMetodoPagamento;
import com.aluraAPI.aluraAPI.persistence.metodopagamento.dto.DadosListagemMetodoPagamento;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("MetodoPagamento")
public class MetodoPagamentoController {

    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMetodoPagamento(@RequestBody @Valid DadosCadastroMetodoPagamento dados){
        metodoPagamentoRepository.save(new MetodoPagamento(dados));
    }

    @GetMapping
    public List<DadosListagemMetodoPagamento> listarMetodoPagamento(){
        return metodoPagamentoRepository.findAll().stream().map(DadosListagemMetodoPagamento::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarMetodoPagamento(@RequestBody @Valid DadosAtualizaMetodoPagamento dados){
        var produto = metodoPagamentoRepository.getReferenceById(dados.id());
        produto.atualizarDadosMetodoPagamento(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMetodoPagamento(@PathVariable Long id){
        var produto = metodoPagamentoRepository.getReferenceById(id);
        produto.inativar();
    }



}
