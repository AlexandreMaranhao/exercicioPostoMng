package com.aluraAPI.aluraAPI.domain.saleProduct.business;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.sale.SaleRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegistredSaleDetails;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterSaleProductItem {

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public static boolean verifyProduct(RegisterSaleProductDto newSaleProductInput){
        boolean productExist = true;

        Long productIdLong = newSaleProductInput.productId();

        if (productIdLong == null){
            productExist = false;
        }

        return(productExist);
    }


    public void registerSaleProductItem(RegisterSaleProductDto newSaleProductInput, RegistredSaleDetails registeredSale){

        if (!verifyProduct(newSaleProductInput)){
            throw new GeneralException(("No registered product with id: " + newSaleProductInput.productId()));
        }
        System.out.println("\n\n\n =============================================\n\n\n\n");
        System.out.println(registeredSale.id());
        System.out.println("aqui1");
        System.out.println("\n\n\n =============================================\n\n\n\n");


        Sale sale = saleRepository.findById(registeredSale.id()).get();
        Product product = productRepository.findById(newSaleProductInput.productId()).get();

        System.out.println("\n\n\n =============================================\n\n\n\n");
        System.out.println(sale);
        System.out.println("aqui2");
        System.out.println("\n\n\n =============================================\n\n\n\n");

        SaleProduct saleProductItem = new SaleProduct(newSaleProductInput.quantity(), sale, product);
        saleProductRepository.save(saleProductItem);
    }

}
