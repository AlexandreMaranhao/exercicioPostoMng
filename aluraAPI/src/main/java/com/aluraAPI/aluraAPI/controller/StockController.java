package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import com.aluraAPI.aluraAPI.domain.stock.StockRepository;
import com.aluraAPI.aluraAPI.domain.stock.dto.ListStockDto;
import com.aluraAPI.aluraAPI.domain.stock.dto.RegisterStockDto;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockDetailDto;
import com.aluraAPI.aluraAPI.domain.stock.dto.UpdateStockDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("estoque")
public class StockController {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerStock(@RequestBody @Valid RegisterStockDto newRegisterStock, UriComponentsBuilder uriBuilder){
        Product product = productRepository.getReferenceById(newRegisterStock.productId());
        Stock stock = new Stock(newRegisterStock, product);

        stockRepository.save(stock);

        var uri = uriBuilder.path("/estoque/{id}").buildAndExpand(stock.getId()).toUri();

        return ResponseEntity.created(uri).body(new RegisterStockDto(stock));
    }

    @GetMapping
    public List<ListStockDto> listarPromocao(){

        return stockRepository.findAll().stream().map(ListStockDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updateStock(@RequestBody @Valid UpdateStockDto updateStockInput){
        var stock = stockRepository.getReferenceById(updateStockInput.id());
        stock.updateStock(updateStockInput);
    }


}
