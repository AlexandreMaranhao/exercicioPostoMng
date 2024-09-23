package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.business.NewSale;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterCompleteSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.UpdateSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.dto.ListSaleDto;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSaleDto;
import com.aluraAPI.aluraAPI.domain.saleProduct.business.RegisterSaleProductItem;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import com.aluraAPI.aluraAPI.helper.SaleJsonExtractor;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    private RegisterSaleProductItem registerSaleProductItem;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity newSale(@RequestBody @Valid RegisterSaleDto newSaleInput){
        newSale.newSale(newSaleInput);
        return ResponseEntity.ok(new Sale(newSaleInput));
    }

    @PostMapping("/completa")
    public ResponseEntity newCompleteSale(@RequestBody @Valid RegisterCompleteSaleDto newSaleInput) throws JsonProcessingException {
        if (newSaleInput.getProducts() == null || newSaleInput.getProducts().isEmpty()) {
            throw new GeneralException("Product list cannot be null or empty");
        }
        System.out.println("\n\n\n =============================================\n\n\n\n");
        System.out.println(newSaleInput);
        System.out.println("\n\n\n =============================================\n\n\n\n");

        System.out.println("\n\n\n =============================================\n\n\n\n");
        System.out.println(newSaleInput.getProducts());
        System.out.println("\n\n\n =============================================\n\n\n\n");

        for (RegisterSaleProductDto product : newSaleInput.getProducts()){

            System.out.println("\n\n\n =============================================\n\n\n\n");
            System.out.println(product);
            System.out.println("\n\n\n =============================================\n\n\n\n");

            var verification = RegisterSaleProductItem.verifyProduct(product);
            if (!verification){
                throw new GeneralException(("No registered product with id: " + product.productId()));
            }
        }
        var newJsonSaleInput = SaleJsonExtractor.extractSaleInput(newSaleInput);
        newSale.newSale(newJsonSaleInput);
        for (RegisterSaleProductDto product : newSaleInput.getProducts()){
            registerSaleProductItem.registerSaleProductItem(product);
        }
        return ResponseEntity.ok("");
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
