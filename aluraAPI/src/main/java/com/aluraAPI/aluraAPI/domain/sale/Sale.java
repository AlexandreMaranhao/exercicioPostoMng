package com.aluraAPI.aluraAPI.domain.sale;

import com.aluraAPI.aluraAPI.domain.costumer.Costumer;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethod;
import com.aluraAPI.aluraAPI.domain.deal.Deal;
import com.aluraAPI.aluraAPI.domain.paymentMethod.PaymentMethodRepository;
import com.aluraAPI.aluraAPI.domain.sale.dto.RegisterSale;
import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.sale.dto.UpdateSale;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Sale(@Valid RegisterSale newSaleInput) {
    }

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public void updateSale(@Valid UpdateSale updateSaleInput){
        if (updateSaleInput.invoiceNumber() != null){
            this.invoiceNumber = updateSaleInput.invoiceNumber();
        }
        if (updateSaleInput.paymentMethodId() != 0.0d) {
            PaymentMethod updatePaymentMethodId = paymentMethodRepository.findById(updateSaleInput.paymentMethodId())
                    .orElseThrow(() -> new GeneralException("No payment method was found with id: " + updateSaleInput.paymentMethodId()));
            this.paymentMethodId = updatePaymentMethodId;
        }


    }

}
