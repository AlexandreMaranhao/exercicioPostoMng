package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.RegisterProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.DetailProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.UpdateProduct;
import com.aluraAPI.aluraAPI.domain.product.dto.ListProduct;
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
    public ResponseEntity registerNewProduct(@RequestBody DetailProduct newProductInput){
        registerNewProduct.registerNewProduct(newProductInput);
        return ResponseEntity.ok(new Product(newProductInput));
    }

    @GetMapping
    public ResponseEntity<List<ListProduct>> listProduct(){
        var list = productRepository.findAll().stream().map(ListProduct::new).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid UpdateProduct updateProductInput){
        var product = productRepository.getReferenceById(updateProductInput.id());
        product.updateProduct(updateProductInput);
        return ResponseEntity.ok(new Product(updateProductInput));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity disableProduct(@PathVariable Long id){
        //var produto = productRepository.getReferenceById(id);
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        product.disable();
        return ResponseEntity.ok(new ListProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity findProduct(@PathVariable Long id){
        // var product = productRepository.getReferenceById(id)
        var product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("No registred product with id: " + id));
        return ResponseEntity.ok(new ListProduct(product));
    }
}
