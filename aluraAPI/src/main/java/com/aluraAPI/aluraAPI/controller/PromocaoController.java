package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.persistence.promocao.Promocao;
import com.aluraAPI.aluraAPI.domain.persistence.promocao.PromocaoRepository;
import com.aluraAPI.aluraAPI.domain.persistence.promocao.dto.DadosAtualizaPromocao;
import com.aluraAPI.aluraAPI.domain.persistence.promocao.dto.DadosCadastroPromocao;
import com.aluraAPI.aluraAPI.domain.persistence.promocao.dto.DadosListagemPromocao;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("promocao")
public class PromocaoController {

    @Autowired
    private PromocaoRepository promocaoRepository;

    @PostMapping
    @Transactional
    public void cadastrarPromocao(@RequestBody @Valid DadosCadastroPromocao dados){
        promocaoRepository.save(new Promocao(dados));
    }

    @GetMapping
    public List<DadosListagemPromocao> listarPromocao(){
        return promocaoRepository.findAll().stream().map(DadosListagemPromocao::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarPromocao(@RequestBody @Valid DadosAtualizaPromocao dados){
        var promocao = promocaoRepository.getReferenceById(dados.id());
        promocao.atualizarDadosPromocao(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirPromocao(@PathVariable Long id){
        var promocao = promocaoRepository.getReferenceById(id);
        promocao.inativar();
    }
}
