package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryUpdateDto;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryRegisterDto;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public void cadastrarCategoria(@RequestBody @Valid CategoryRegisterDto inputedData){
        categoryRepository.save(new Category(inputedData));
    }

    @GetMapping
    public List<CategoryListDto> listarCategoria(){
        return categoryRepository.findAll().stream().map(CategoryListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarCategoria(@RequestBody @Valid CategoryUpdateDto inputedData){
        var categoria = categoryRepository.getReferenceById(inputedData.id());
        categoria.updateCategory(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirCategoria(@PathVariable Long id){
        var categoria = categoryRepository.getReferenceById(id);
        categoria.disable();
    }



}
