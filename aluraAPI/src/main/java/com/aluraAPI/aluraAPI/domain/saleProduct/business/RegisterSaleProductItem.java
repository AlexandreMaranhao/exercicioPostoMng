package com.aluraAPI.aluraAPI.domain.saleProduct.business;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterSaleProductItem {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private static ProductRepository productRepository;

    public static boolean verifyProduct(RegisterSaleProductDto newSaleProductInput){
        var product = productRepository.findById(newSaleProductInput.productId());

        boolean productExist = product.isPresent();

        return(productExist);
    }


    public void registerSaleProductItem(RegisterSaleProductDto newSaleProductInput){
        if (!verifyProduct(newSaleProductInput)){
            throw new GeneralException(("No registered product with id: " + newSaleProductInput.productId()));
        }

        var sale = saleRepository.findById(newSaleProductInput.saleId()).get();
        var product = productRepository.findById(newSaleProductInput.productId()).get();

        var saleProductItem = new SaleProduct(newSaleProductInput.quantity(), sale, product);
        saleProductRepository.save(saleProductItem);
    }



}
