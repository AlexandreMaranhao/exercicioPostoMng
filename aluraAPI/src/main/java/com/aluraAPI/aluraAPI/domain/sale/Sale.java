package com.aluraAPI.aluraAPI.domain.sale;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.sale.dto.UpdateSale;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date date;
    private float  amount;
    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id", referencedColumnName = "id")
    private Deal dealId;

    private boolean refound;




    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, Costumer costumer, User user, Deal deal) {
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, Costumer costumer, User user) {
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, User user, Deal deal) {
    }

    public Sale(LocalDateTime sellDate, @NotNull float amount, String invoiceNumber, PaymentMethod paymentMethod, User user) {
    }



    public void updateSale(@Valid UpdateSale newSaleInput){
        if (newSaleInput.invoiceNumber() != null){
            this.invoiceNumber = newSaleInput.invoiceNumber();
        }
 /*       if (dados.metodopagamentoId() != 0.0d){
            this.metodopagamentoId = dados.metodopagamentoId();
        }
        if (dados.clienteId() != 0.0d){
            this.clienteId = dados.clienteId();
        }*/

    }

}
