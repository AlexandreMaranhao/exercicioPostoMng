package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.CategoriaRepository;
import com.aluraAPI.aluraAPI.domain.persistence.produto.Produto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.ProdutoRepository;
import com.aluraAPI.aluraAPI.domain.persistence.produto.business.CadastrarProduto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosAtualizarProduto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosCadastroProduto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosListagemProduto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastrarProduto cadastro;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Valid DadosCadastroProduto dados){
    //    produtoRepository.save(new Produto(dados));
        cadastro.cadastrarProduto(dados);
        return ResponseEntity.ok(new DadosCadastroProduto(dados.nome(), dados.preco(), dados.categoriaId()));//TODO: alterar dto para ResponseCadastroProduto(retornando todos os campos)
    }

    @GetMapping
    public List<DadosListagemProduto> listarProduto(){
        return produtoRepository.findAll().stream().map(DadosListagemProduto::new).toList();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarProduto(@RequestBody @Valid DadosAtualizarProduto dados){
        //var produto = produtoRepository.getReferenceById(dados.id());
        //produto.atualizarDadosProduto(dados);
        return ResponseEntity.ok(new DadosCadastroProduto(dados.nome(), dados.preco(), dados.categoriaId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirProduto(@PathVariable Long id){
        var produto = produtoRepository.getReferenceById(id);
        produto.inativar();
        return ResponseEntity.noContent().build();
    }
}
