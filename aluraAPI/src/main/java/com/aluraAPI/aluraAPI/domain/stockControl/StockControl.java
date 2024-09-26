package com.aluraAPI.aluraAPI.domain.stockControl;

import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProduct;
import com.aluraAPI.aluraAPI.domain.stock.Stock;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlRegisterEntranceDto;
import com.aluraAPI.aluraAPI.domain.stockControl.dto.StockControlRegisterSaleDto;
import com.aluraAPI.aluraAPI.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "stock_control")
@Entity(name = "StockControl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StockControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private float quantity;
    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_product_id", referencedColumnName = "id")
    private SaleProduct saleProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stockId;


    public StockControl(@Valid StockControlRegisterSaleDto newRegisterStockControlSale,LocalDateTime registerStockControlDate, SaleProduct saleProduct, User user, Stock stock) {
        this.date = registerStockControlDate;
        this.quantity = newRegisterStockControlSale.quantity();
        this.type = newRegisterStockControlSale.type();
        this.saleProductId = saleProduct;
        this.userId = user;
        this.stockId = stock;
    }

    public StockControl(@Valid StockControlRegisterEntranceDto newRegisterStockControlEntrance, LocalDateTime registerStockControlDate, User user, Stock stock) {
        this.date = registerStockControlDate;
        this.quantity = newRegisterStockControlEntrance.quantity();
        this.type = newRegisterStockControlEntrance.type();
        this.userId = user;
        this.stockId = stock;
    }
}


