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
    public void registerCategory(@RequestBody @Valid CategoryRegisterDto newCategoryInput){
        categoryRepository.save(new Category(newCategoryInput));
    }

    @GetMapping
    public List<CategoryListDto> listCategory(){
        return categoryRepository.findAll().stream().map(CategoryListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updateCategory(@RequestBody @Valid CategoryUpdateDto updateCategoryInput){
        Category category = categoryRepository.getReferenceById(updateCategoryInput.id());
        category.updateCategory(updateCategoryInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivateCategory(@PathVariable Long id){
        Category category = categoryRepository.getReferenceById(id);
        category.disable();
    }



}
