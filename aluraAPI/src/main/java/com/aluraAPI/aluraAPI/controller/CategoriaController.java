package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.persistence.categoria.Categoria;
import com.aluraAPI.aluraAPI.persistence.categoria.CategoriaRepository;
import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosAtualizaCategoria;
import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosCadastroCategoria;
import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosListagemCategoria;
import com.aluraAPI.aluraAPI.persistence.produto.Produto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosAtualizarProduto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosCadastroProduto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosListagemProduto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public void cadastrarCategoria(@RequestBody @Valid DadosCadastroCategoria dados){
        categoriaRepository.save(new Categoria(dados));
    }

    @GetMapping
    public List<DadosListagemCategoria> listarCategoria(){
        return categoriaRepository.findAll().stream().map(DadosListagemCategoria::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarCategoria(@RequestBody @Valid DadosAtualizaCategoria dados){
        var produto = categoriaRepository.getReferenceById(dados.id());
        produto.atualizarDadosCategoria(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirCategoria(@PathVariable Long id){
        var produto = categoriaRepository.getReferenceById(id);
        produto.inativar();
    }



}
