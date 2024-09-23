package com.aluraAPI.aluraAPI.domain.product;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.product.dto.RegisterProductDto;
import com.aluraAPI.aluraAPI.domain.product.dto.UpdateProductDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryId;

    private boolean active;


    public Product(String name, float price, Category categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.active = true;
    }

    public Product(RegisterProductDto inputedData) {
    }

    public Product(@Valid UpdateProductDto updateProductDtoInput) {
    }

    public void updateProduct(@Valid UpdateProductDto newProductInput) {
        if (newProductInput.name() != null){
            this.name = newProductInput.name();
        }
        if (newProductInput.price() != 0.0f){
            this.price = newProductInput.price();
        }
    }

    public void disable() {
        this.active = false;
    }

    public Category getCategory() {
        return categoryId;
    }


}
