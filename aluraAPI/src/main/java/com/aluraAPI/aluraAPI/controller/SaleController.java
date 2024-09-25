package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.business.NewSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.*;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import com.aluraAPI.aluraAPI.helper.SaleJsonExtractor;
import com.fasterxml.jackson.core.JsonProcessingException;
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
/*
    @PostMapping
    public ResponseEntity newSale(@RequestBody @Valid RegisterSaleDto newSaleInput){
        newSale.newSale(newSaleInput);
        return ResponseEntity.ok(new Sale(newSaleInput));
    }
*/
    @PostMapping("/completa")
    public ResponseEntity newCompleteSale(@RequestBody @Valid RegisterCompleteSaleDto newSaleInput, UriComponentsBuilder uriBuilder)  {
        RegistredSaleDetails newCompleteSale = newSale.realizeCompleteSale(newSaleInput);
        //return ResponseEntity.ok("");
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(newCompleteSale.id()).toUri();
        return ResponseEntity.created(uri).body(newCompleteSale);
    }

    @GetMapping
    public List<ListSaleDto> listSale(){

        return saleRepository.findAll().stream().map(ListSaleDto::new).toList();
    }

    @PutMapping
    public void atualizarVenda(@RequestBody @Valid UpdateSaleDto updateSaleInput){
        var venda = saleRepository.getReferenceById(updateSaleInput.id());
        venda.updateSale(updateSaleInput);
    }
}
