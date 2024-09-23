package com.aluraAPI.aluraAPI.domain.saleProduct.business;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.NewSaleProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewSaleProduct {
    @Autowired
    SaleProductRepository saleProductRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ProductRepository productRepository;

    public NewSaleProductDto newSaleProduct(NewSaleProductDto newSaleProductInput){
        if(newSaleProductInput.ProductId() == 0.0d){
            throw new GeneralException(("Sale can not be created without product"));
        }

    }
}
