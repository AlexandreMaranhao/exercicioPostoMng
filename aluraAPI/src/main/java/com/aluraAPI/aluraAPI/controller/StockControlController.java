package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import com.aluraAPI.aluraAPI.domain.stock.StockRepository;
import com.aluraAPI.aluraAPI.domain.stockControl.StockControl;
import com.aluraAPI.aluraAPI.domain.stockControl.StockControlRepository;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlListDto;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlRegisterEntranceDto;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlRegisterSaleDto;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlRegisteredSaleDto;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("controleestoque")
public class StockControlController {
    @Autowired
    private StockControlRepository stockControlRepository;
    @Autowired
    private SaleProductRepository saleProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockRepository stockRepository;


    @PostMapping("/reabastecer")
    @Transactional
    public ResponseEntity entranceStockControl(@RequestBody @Valid StockControlRegisterEntranceDto newRegisterStockControlEntrance, UriComponentsBuilder uriBuilder){
        User user = userRepository.findById(newRegisterStockControlEntrance.userId()).get();
        Stock stock = stockRepository.findById(newRegisterStockControlEntrance.stockId()).get();
        LocalDateTime registerStockControlDate = LocalDateTime.now();
        StockControl regristredStockControl = new StockControl(newRegisterStockControlEntrance,registerStockControlDate, user, stock);

        stockControlRepository.save(regristredStockControl);

       var uri = uriBuilder.path("/controleestoque/{id}").buildAndExpand(regristredStockControl.getId()).toUri();

       return ResponseEntity.created(uri).body(new StockControlRegisterEntranceDto(regristredStockControl));
    }

    @PostMapping("/venda")
    @Transactional
    public ResponseEntity sellStockControl(@RequestBody @Valid StockControlRegisterSaleDto newRegisterStockControlSale, UriComponentsBuilder uriBuilder){
        SaleProduct saleProduct = saleProductRepository.getReferenceById(newRegisterStockControlSale.saleProductId());
        User user = userRepository.getReferenceById(newRegisterStockControlSale.userId());
        Stock stock = stockRepository.getReferenceById(newRegisterStockControlSale.stockId());
        LocalDateTime registerStockControlDate = LocalDateTime.now();
        StockControl registredStockControl = new StockControl(newRegisterStockControlSale,registerStockControlDate, saleProduct, user, stock);

        stockControlRepository.save(registredStockControl);

        var uri = uriBuilder.path("/controleestoque/{id}").buildAndExpand(registredStockControl.getId()).toUri();

        return ResponseEntity.created(uri).body(new StockControlRegisteredSaleDto(registredStockControl));
    }

    @GetMapping
    public ResponseEntity<List<StockControlListDto>> listStockControl(){
        var list = stockControlRepository.findAll().stream().map(StockControlListDto::new).toList();
        return ResponseEntity.ok(list);
    }



}