package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryUpdateDto;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryRegisterDto;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryListDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerCategory(@RequestBody @Valid CategoryRegisterDto newCategoryInput, UriComponentsBuilder uriBuilder){
        Category category = categoryRepository.save(new Category(newCategoryInput));

        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDto>> listCategory(){
        var list = categoryRepository.findAll().stream()
                .map(category -> category.castCategoryListDto())
                .toList();

        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCategory(@RequestBody @Valid CategoryUpdateDto updateCategoryInput){
        Category category = categoryRepository.getReferenceById(updateCategoryInput.id());
        category.updateCategory(updateCategoryInput);

        return ResponseEntity.ok(new Category(updateCategoryInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivateCategory(@PathVariable Long id){
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred category with id: " + id));
        category.disable();
        return ResponseEntity.noContent().build();
    }
}
