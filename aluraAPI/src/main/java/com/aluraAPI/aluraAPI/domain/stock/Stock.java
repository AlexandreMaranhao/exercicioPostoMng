package com.aluraAPI.aluraAPI.domain.stock;

import com.aluraAPI.aluraAPI.domain.category.dto.CategoryUpdateDto;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockRegisterDto;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "stock")
@Entity(name ="Stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float quantity;
    private LocalDateTime validity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;



    public Stock(@Valid StockRegisterDto newRegisterStockInput, Product product) {
        this.quantity = newRegisterStockInput.quantity();
        this.validity = newRegisterStockInput.validity();
        this.productId = product;
    }

    public void updateStock (@Valid Float updateStock) {
        if (updateStock != null){
            this.quantity = updateStock;
        }
    }
}
