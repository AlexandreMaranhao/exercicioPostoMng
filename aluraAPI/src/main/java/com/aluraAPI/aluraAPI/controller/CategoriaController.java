package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.Categoria;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.CategoriaRepository;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.dto.DadosAtualizaCategoria;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.dto.DadosCadastroCategoria;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.dto.DadosListagemCategoria;
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
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.atualizarDadosCategoria(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirCategoria(@PathVariable Long id){
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.inativar();
    }



}
