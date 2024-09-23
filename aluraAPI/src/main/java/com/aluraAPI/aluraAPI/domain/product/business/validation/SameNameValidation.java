

package com.aluraAPI.aluraAPI.domain.product.business.validation;

import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.dto.DetailProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SameNameValidationProduct")
public class SameNameValidation implements ProductValidation {

    @Autowired
    ProductRepository productRepository;

    public void validate(DetailProductDto inputedData){

        var product = productRepository.findByName(inputedData.name());

        if (product != null){
            throw new GeneralException(("This Product is already registred"));
        }

    }
}


