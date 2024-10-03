package com.aluraAPI.aluraAPI.domain.saleProduct.business;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisteredDetailsDto;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.SaleProductRegisterDto;
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
    private ProductRepository productRepository;

    public static boolean verifyProduct(SaleProductRegisterDto newSaleProductInput){
        boolean productExist = true;

        Long productIdLong = newSaleProductInput.productId();

        if (productIdLong == null){
            productExist = false;
        }

        return(productExist);
    }


    public SaleProduct registerSaleProductItem(SaleProductRegisterDto newSaleProductInput, SaleRegisteredDetailsDto registeredSale){

        if (!verifyProduct(newSaleProductInput)){
            throw new GeneralException(("No registered product with id: " + newSaleProductInput.productId()));
        }
        Sale sale = saleRepository.findById(registeredSale.id()).get();
        Product product = productRepository.findById(newSaleProductInput.productId()).get();
        SaleProduct saleProductItem = new SaleProduct(newSaleProductInput.quantity(), sale, product);
        saleProductRepository.save(saleProductItem);

        return saleProductItem;
    }

}
