package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.ListSaleProductDto;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.NewSaleProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendaproduto")
public class SaleProductController {

    @Autowired
    private SaleProductRepository saleProductRepository;


    @PostMapping
    @Transactional
    public ResponseEntity newSaleProduct(@RequestBody @Valid NewSaleProductDto newSaleProductInput){
        var newSlae = newSaleProduct(newSaleProductInput);
        return ResponseEntity.ok(new Product());
    }

    @GetMapping
    public List<ListSaleProductDto> listSaleProduct(){
        return saleProductRepository.findAll().stream().map(ListSaleProductDto::new).toList();
    }


}

