package com.aluraAPI.aluraAPI.domain.saleProduct;

import jakarta.persistence.*;
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
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private long productId;


    public VendaProduto(@Valid DadosCadastroVendaProduto dados) {
        this.saleId = dados.saleId();
        this.productId = dados.productId();
        this.quantity = dados.quantity();
    }
*/

}
