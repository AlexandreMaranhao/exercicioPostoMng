

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.dto.DetailProduct;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SameNameValidationProduct")
public class SameNameValidation implements ProductValidation {

    @Autowired
    ProductRepository productRepository;

    public void validate(DetailProduct inputedData){
        System.out.println("============================================");
        System.out.println(inputedData);
        System.out.println("============================================");
        var product = productRepository.findByName(inputedData.name());
        System.out.println("============================================");
        System.out.println(product);
        System.out.println("============================================");
        if (product != null){
            throw new GeneralException(("Produto ja cadastrado"));
        }

    }
}


