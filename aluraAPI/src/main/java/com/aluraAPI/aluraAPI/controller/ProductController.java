package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.RegisterProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.RegisterProductDto;
import com.aluraAPI.aluraAPI.domain.product.dto.UpdateProductDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ListProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RegisterProduct registerNewProduct;

    @PostMapping
    @Transactional
    public ResponseEntity registerNewProduct(@RequestBody RegisterProductDto newProductInput){
        registerNewProduct.registerNewProduct(newProductInput);
        return ResponseEntity.ok(new Product(newProductInput));
    }

    @GetMapping
    public ResponseEntity<List<ListProductDto>> listProduct(){
        var list = productRepository.findAll().stream().map(ListProductDto::new).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid UpdateProductDto updateProductDtoInput){
        var product = productRepository.getReferenceById(updateProductDtoInput.id());
        product.updateProduct(updateProductDtoInput);
        return ResponseEntity.ok(new Product(updateProductDtoInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity disableProduct(@PathVariable Long id){
        //var produto = productRepository.getReferenceById(id);
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        product.disable();
        return ResponseEntity.ok(new ListProductDto(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity findProduct(@PathVariable Long id){
        // var product = productRepository.getReferenceById(id)
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        return ResponseEntity.ok(new ListProductDto(product));
    }
}
