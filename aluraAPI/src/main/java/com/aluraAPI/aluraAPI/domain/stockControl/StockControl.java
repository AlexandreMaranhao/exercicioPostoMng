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

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @OneToOne
    @JoinColumn(name = "sale_product_id", referencedColumnName = "id")
    private SaleProduct saleProductId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stockId;


    public StockControl(@Valid StockControlRegisterSaleDto registerStockControlSale, LocalDateTime registerStockControlDate, SaleProduct saleProduct, User user, Stock stock) {
        this.date = registerStockControlDate;
        this.quantity = registerStockControlSale.quantity();
        this.type = registerStockControlSale.type();
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

    public StockControl(@Valid LocalDateTime date, Float quantity, Type type, SaleProduct saleProduct, User user, Stock stock) {
        this.date = date;
        this.quantity = quantity;
        this.type = type;
        this.saleProductId = saleProduct;
        this.userId = user;
        this.stockId = stock;
    }
}


