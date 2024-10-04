package com.aluraAPI.aluraAPI.domain.sale;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleRegisteredDetailsDto;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.sale.dto.SaleUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "sale")
@Entity(name ="Sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private float  amount;
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumerId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "deal_id", referencedColumnName = "id")
    private Deal dealId;

    private boolean refound;

    private int loyaltyPoints;




    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, Costumer costumer, User user, Deal deal) {
        this.date = sellDate;
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.paymentMethodId = paymentMethod;
        this.costumerId = costumer;
        this.userId = user;
        this.dealId = deal;
        this.refound = false;
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, Costumer costumer, User user) {
        this.date = sellDate;
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.paymentMethodId = paymentMethod;
        this.costumerId = costumer;
        this.userId = user;
        this.refound = false;
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, User user, Deal deal) {
        this.date = sellDate;
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.paymentMethodId = paymentMethod;
        this.userId = user;
        this.dealId = deal;
        this.refound = false;
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, User user) {
        this.date = sellDate;
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
        this.paymentMethodId = paymentMethod;
        this.userId = user;
        this.refound = false;
    }


    public SaleRegisteredDetailsDto changeToSaleRegisteredDetailsDto (){
        return new SaleRegisteredDetailsDto(
                this.id,
                this.date,
                this.amount,
                this.invoiceNumber,
                this.paymentMethodId,
                this.costumerId,
                this.userId,
                this.dealId,
                this.refound);
    }

/*

    public void updateSale(@Valid SaleUpdateDto updateSaleInput){
        if (updateSaleInput.invoiceNumber() != null){
            this.invoiceNumber = updateSaleInput.invoiceNumber();
        }
        if (dados.metodopagamentoId() != 0.0d){
            this.metodopagamentoId = dados.metodopagamentoId();
        }
        if (dados.clienteId() != 0.0d){
            this.clienteId = dados.clienteId();
        }

    }*/

}
