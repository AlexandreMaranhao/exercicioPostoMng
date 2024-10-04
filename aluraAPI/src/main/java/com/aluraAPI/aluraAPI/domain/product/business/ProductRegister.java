package com.aluraAPI.aluraAPI.domain.product.business;


import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.category.CategoryRepository;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.product.ProductRepository;
import com.aluraAPI.aluraAPI.domain.product.business.validation.ProductValidation;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductDetailDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductRegisterDto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRegister {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private List<ProductValidation> validators;

    public ProductDetailDto registerNewProduct(ProductRegisterDto newProductInput){
        Long categoryIdLong =  newProductInput.categoryId();
        if((newProductInput.name() == null) || (categoryIdLong == null)){
            throw new GeneralException(("Name and Category are mandatory fields"));
        }

        if(!categoryRepository.existsById(newProductInput.categoryId())){
            throw new GeneralException(("Informed Category value does not exist"));
        }

        validators.forEach(v -> v.validate(newProductInput));


        Category category = categoryRepository.findById(newProductInput.categoryId()).get();
        Product product = new Product(newProductInput.name(), newProductInput.price(), category);


        productRepository.save(product);

        return product.castToProductDetailDto();
    }
}
