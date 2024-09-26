package com.aluraAPI.aluraAPI.domain.stock;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.stock.dto.StockRegisterDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;



    public Stock(@Valid StockRegisterDto newRegisterStock, Product product) {
        this.quantity = newRegisterStock.quantity();
        this.validity = newRegisterStock.validity();
        this.productId = product;
    }
}
