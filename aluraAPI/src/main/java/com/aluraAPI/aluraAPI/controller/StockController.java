package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import com.aluraAPI.aluraAPI.domain.stock.StockRepository;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockListDto;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockRegisterDto;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity registerStock(@RequestBody @Valid StockRegisterDto newRegisterStockInput, UriComponentsBuilder uriBuilder){
        Product product = productRepository.getReferenceById(newRegisterStockInput.productId());
        Stock stock = new Stock(newRegisterStockInput, product);

        stockRepository.save(stock);

        var uri = uriBuilder.path("/estoque/{id}").buildAndExpand(stock.getId()).toUri();

        return ResponseEntity.created(uri).body(new StockRegisterDto(stock));
    }

    @GetMapping
    public ResponseEntity<List<StockListDto>> listStock(){
        var list = stockRepository.findAll().stream().map(StockListDto::new).toList();
        return ResponseEntity.ok(list);
    }
}
