package com.aluraAPI.aluraAPI.domain.product.business;


import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.validation.ProductValidation;
import com.aluraAPI.aluraAPI.domain.product.dto.DetailProduct;
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

    public DetailProduct registerProduct(DetailProduct newProductInput){


        if(!categoryRepository.existsById(newProductInput.categoryId())){
            throw new GeneralException(("Categoria informada não existe"));
        }

        validators.forEach(v -> v.validate(newProductInput));


        var categoria = categoryRepository.findById(newProductInput.categoryId()).get();
        var product = new Product(newProductInput.name(), newProductInput.price(), categoria);

        productRepository.save(product);
        return new DetailProduct(product);

    }
}
