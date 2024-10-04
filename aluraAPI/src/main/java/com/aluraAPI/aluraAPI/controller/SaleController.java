package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.business.NewSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.*;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("vendas")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private NewSale newSale;

    @Autowired
    private RegisterSaleProductItem registerSaleProductItem;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/completa")
    public ResponseEntity registerSale(@RequestBody @Valid SaleCompleteRegisterDto newSaleInput, UriComponentsBuilder uriBuilder)  {
        SaleRegisteredDetailsDto newCompleteSale = newSale.realizeCompleteSale(newSaleInput);
        SaleReceiptDto receipt = newSale.generateReceipt(newCompleteSale, newSaleInput);

        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(newCompleteSale.id()).toUri();
        return ResponseEntity.created(uri).body(receipt);
    }

    @GetMapping
    public ResponseEntity<List<SaleListDto>> listSale(){
        var list =  saleRepository.findAll().stream()
                .map(sale -> sale.castoToSaleListDtl())
                .toList();
        return ResponseEntity.ok(list);
    }
}
