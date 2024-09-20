package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.persistence.venda.dto.DadosAtualizarVenda;
import com.aluraAPI.aluraAPI.domain.persistence.venda.dto.DadosListagemVenda;
import com.aluraAPI.aluraAPI.domain.persistence.venda.Venda;
import com.aluraAPI.aluraAPI.domain.persistence.venda.VendaRepository;
import com.aluraAPI.aluraAPI.domain.persistence.venda.dto.DadosCadastroVenda;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @PostMapping
    public void cadastrarVenda(@RequestBody @Valid DadosCadastroVenda dados){
        vendaRepository.save(new Venda(dados));
    }

    @GetMapping
    public List<DadosListagemVenda> listarVendas(){
        return vendaRepository.findAll().stream().map(DadosListagemVenda::new).toList();
    }

    @PutMapping
    public void atualizarVenda(@RequestBody @Valid DadosAtualizarVenda dados){
        var venda = vendaRepository.getReferenceById(dados.id());
        venda.atualizarDadosVenda(dados);

    }
}
