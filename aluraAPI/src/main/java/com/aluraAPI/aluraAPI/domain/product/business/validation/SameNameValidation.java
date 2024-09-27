

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductRegisterDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SameNameValidationProduct")
public class SameNameValidation implements ProductValidation {

    @Autowired
    ProductRepository productRepository;

    public void validate(ProductRegisterDto inputedData){

        Product product = productRepository.findByName(inputedData.name());

        if (product != null){
            throw new GeneralException(("This Product is already registred"));
        }

    }
}


