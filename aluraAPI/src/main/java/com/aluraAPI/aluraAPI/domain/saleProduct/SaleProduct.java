package com.aluraAPI.aluraAPI.domain.saleProduct;

import com.aluraAPI.aluraAPI.domain.category.Category;
import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "sale_product")
@Entity(name = "SaleProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale saleId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;


    public SaleProduct(@NotNull float quantity, Sale saleId, Product productId) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Sale getSale() {
        return saleId;
    }

    public Product getProduct() {
        return productId;
    }

}
