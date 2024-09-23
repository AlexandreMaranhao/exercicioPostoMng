package com.aluraAPI.aluraAPI.domain.saleProduct;

import com.aluraAPI.aluraAPI.domain.product.Product;
import com.aluraAPI.aluraAPI.domain.sale.Sale;
import com.aluraAPI.aluraAPI.domain.saleProduct.dto.NewSaleProductDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;


    public SaleProduct(@Valid NewSaleProductDto newSaleProductDtoInput, Sale saleId, Product productId) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = newSaleProductDtoInput.quantity();
    }


    public SaleProduct(@Valid NewSaleProductDto newSaleProductInput) {
    }
}
