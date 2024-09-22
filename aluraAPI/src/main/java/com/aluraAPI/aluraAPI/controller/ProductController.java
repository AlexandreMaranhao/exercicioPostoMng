package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.dto.DetailProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.UpdateProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.RegisterProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.ListProduct;
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
    private
    ProductRepository productRepository;

    @Autowired
    private
    com.aluraAPI.aluraAPI.domain.product.business.RegisterProduct registerProduct;

    @PostMapping
    @Transactional
    public ResponseEntity registerProduct(@RequestBody DetailProduct inputedData){
        registerProduct.registerProduct(inputedData);
        return ResponseEntity.ok(new RegisterProduct(inputedData.name(), inputedData.price(), inputedData.categoryId()));
    }

    @GetMapping
    public ResponseEntity<List<ListProduct>> listProduct(){
        var list = productRepository.findAll().stream().map(ListProduct::new).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid UpdateProduct inputedData){
        var product = productRepository.getReferenceById(inputedData.id());
        product.updateProduct(inputedData);
        return ResponseEntity.ok(new RegisterProduct(inputedData.name(), inputedData.price(), inputedData.categoryId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity disableProduct(@PathVariable Long id){
        var produto = productRepository.getReferenceById(id);
        produto.disable();
        return ResponseEntity.noContent().build();
    }
}
