package com.aluraAPI.aluraAPI.domain.product.business;


import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.validation.ProductValidation;
import com.aluraAPI.aluraAPI.domain.product.dto.RegisterProductDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterProduct {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private List<ProductValidation> validators;

    public RegisterProductDto registerNewProduct(RegisterProductDto newProductInput){
        if((newProductInput.name() == null) || (newProductInput.categoryId() == 0.0d)){
            throw new GeneralException(("Name and Category are mandatory fields"));
        }

        if(!categoryRepository.existsById(newProductInput.categoryId())){
            throw new GeneralException(("Informed Category value does not exist"));
        }

        validators.forEach(v -> v.validate(newProductInput));


        var categoria = categoryRepository.findById(newProductInput.categoryId()).get();
        var product = new Product(newProductInput.name(), newProductInput.price(), categoria);

        productRepository.save(product);
        return new RegisterProductDto(product);

    }
}
