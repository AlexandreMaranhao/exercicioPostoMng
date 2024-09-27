package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendaproduto")
public class SaleProductController {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private RegisterSaleProductItem registerNewSaleProductItem;
/*
    @PostMapping
    @Transactional
    public void newSaleProduct(@RequestBody @Valid RegisterSaleProductDto newSaleProductInput){
        registerNewSaleProductItem.registerSaleProductItem(newSaleProductInput);
       // saleProductRepository.save(new SaleProduct(newSaleProductInput));
    }
*/
    @GetMapping
    public List<SaleProductListDto> listSaleProduct(){
        return saleProductRepository.findAll().stream().map(SaleProductListDto::new).toList();
    }


}

