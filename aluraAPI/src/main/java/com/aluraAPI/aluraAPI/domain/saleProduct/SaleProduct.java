package com.aluraAPI.aluraAPI.domain.saleProduct;

import com.aluraAPI.aluraAPI.domain.saleProduct.dto.RegisterSaleProductDto;
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
    private long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private long productId;


    public SaleProduct(@Valid RegisterSaleProductDto saleProductInput) {
        this.saleId = saleProductInput.saleId();
        this.productId = saleProductInput.productId();
        this.quantity = saleProductInput.quantity();
    }


}
