package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.ProductRegister;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductDetailDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductRegisterDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductUpdateDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRegister registerNewProduct;

    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping
    @Transactional
    public ResponseEntity registerNewProduct(@RequestBody ProductRegisterDto registerNewProductInput, UriComponentsBuilder uriBuilder){
        Category categoryId = categoryRepository.findById(registerNewProductInput.categoryId()).get();
        Product product = new Product(registerNewProductInput, categoryId);

        ProductDetailDto response = registerNewProduct.registerNewProduct(registerNewProductInput);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailDto>> listProduct(){
        var list = productRepository.findAll().stream()
                .map(product -> product.castToProductDetailDto())
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductUpdateDto updateProductInput){
        Product product = productRepository.getReferenceById(updateProductInput.id());
        product.updateProduct(updateProductInput);
        return ResponseEntity.ok(new Product(updateProductInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivateProduct(@PathVariable Long id){
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        product.disable();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findProduct(@PathVariable Long id){
        // var product = productRepository.getReferenceById(id)
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        return ResponseEntity.ok(product.castToProductDetailDto());
    }
}
