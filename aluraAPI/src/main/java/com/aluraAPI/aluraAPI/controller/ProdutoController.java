package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.persistence.produto.Produto;
import com.aluraAPI.aluraAPI.persistence.produto.ProdutoRepository;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosAtualizarProduto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosCadastroProduto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosListagemProduto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public void cadastrarProduto(@RequestBody @Valid DadosCadastroProduto dados){
        produtoRepository.save(new Produto(dados));
    }

    @GetMapping
    public List<DadosListagemProduto> listarProduto(){
        return produtoRepository.findAll().stream().map(DadosListagemProduto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarProduto(@RequestBody @Valid DadosAtualizarProduto dados){
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarDadosProduto(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirProduto(@PathVariable Long id){
        produtoRepository.getReferenceById(id);
    }
}
