package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.business.NewSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.UpdateSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.ListSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSaleDto;
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
    public ResponseEntity newSale(@RequestBody @Valid RegisterSaleDto newSaleInput){
        newSale.newSell(newSaleInput);
        return ResponseEntity.ok(new Sale(newSaleInput));
    }

    @GetMapping
    public List<ListSaleDto> listarVendas(){

        return saleRepository.findAll().stream().map(ListSaleDto::new).toList();
    }

    @PutMapping
    public void atualizarVenda(@RequestBody @Valid UpdateSaleDto updateSaleInput){
        var venda = saleRepository.getReferenceById(updateSaleInput.id());
        venda.updateSale(updateSaleInput);
    }
}
