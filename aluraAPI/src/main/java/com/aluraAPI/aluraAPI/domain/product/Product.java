package com.aluraAPI.aluraAPI.domain.product;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductDetailDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductRegisterDto;
import com.aluraAPI.aluraAPI.domain.product.dto.ProductUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "product")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryId;

    private boolean active;


    public Product(String name, float price, Category categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.active = true;
    }

    public Product(ProductRegisterDto newProductInput, Category categoryId) {
        this.name = newProductInput.name();
        this.price = newProductInput.price();
        this.categoryId = categoryId;
    }

    public Product(ProductUpdateDto updateProductInput) {
        this.name = updateProductInput.name();
        this.price = updateProductInput.price();
        this.categoryId = getCategoryId();
    }

   /*
    public ProductListDto castToProductListDto(){
        return new ProductListDto(
                this.id,
                this.name,
                this.price,
                this.categoryId,
                this.isActive()
        );
    }

    */
    public ProductDetailDto castToProductDetailDto(){
        return new ProductDetailDto(
                this.id,
                this.name,
                this.price,
                this.categoryId,
                this.isActive()
        );
    }

    public void updateProduct(@Valid ProductUpdateDto updateProductInput) {
        if (updateProductInput.name() != null){
            this.name = updateProductInput.name();
        }
        Float priceLong = updateProductInput.price();
        if (priceLong != null){
            this.price = updateProductInput.price();
        }
    }

    public void disable() {
        this.active = false;
    }

    public Category getCategory() {
        return categoryId;
    }


}
