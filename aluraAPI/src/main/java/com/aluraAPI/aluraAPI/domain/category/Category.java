package com.aluraAPI.aluraAPI.domain.category;

import com.aluraAPI.aluraAPI.domain.category.dto.CategoryUpdateDto;
import com.aluraAPI.aluraAPI.domain.category.dto.CategoryRegisterDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "category")
@Entity(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean active;


    public Category(@Valid CategoryRegisterDto newCategoryInput) {
        this.name = newCategoryInput.name();
        this.active = true;
    }


    public void updateCategory (@Valid CategoryUpdateDto updateCategoryInput) {
        if (updateCategoryInput.name() != null){
            this.name = updateCategoryInput.name();
        }
    }

    public void disable() {this.active = false;}

}
