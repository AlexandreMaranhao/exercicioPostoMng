package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.business.NewSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.UpdateSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.ListSale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSale;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendas")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private NewSale newSale;

    @PostMapping
    public ResponseEntity newSale(@RequestBody @Valid RegisterSale newSaleInput){
        newSale.newSell(newSaleInput);
        return ResponseEntity.ok(new Sale(newSaleInput));
    }

    @GetMapping
    public List<ListSale> listarVendas(){
        return saleRepository.findAll().stream().map(ListSale::new).toList();
    }

    @PutMapping
    public void atualizarVenda(@RequestBody @Valid UpdateSale inputedData){
        var venda = saleRepository.getReferenceById(inputedData.id());
        venda.updateSale(inputedData);
    }
}
