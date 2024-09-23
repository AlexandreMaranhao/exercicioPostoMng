package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.ListSaleProductDto;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendaproduto")
public class SaleProductController {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @PostMapping
    @Transactional
    public void newSaleProduct(@RequestBody @Valid RegisterSaleProductDto newSaleProductInput){
        saleProductRepository.save(new SaleProduct(newSaleProductInput));
    }

    @GetMapping
    public List<ListSaleProductDto> listarVendaProduto(){
        return saleProductRepository.findAll().stream().map(ListSaleProductDto::new).toList();
    }


}

