package com.aluraAPI.aluraAPI.domain.paymentMethod;

import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.UpdatePaymentMethodDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.RegisterPaymentMethodDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "payment_method")
@Entity(name = "PaymentMethod")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String method;
    private String discount;
    private boolean active;

    public PaymentMethod(RegisterPaymentMethodDto newPaymentMethodInput) {
        this.method = newPaymentMethodInput.method();
        this.discount = newPaymentMethodInput.discount();
        this.active = true;
    }



    public void updatePaymentMethod(@Valid UpdatePaymentMethodDto newPaymentMethodInput) {
        if (newPaymentMethodInput.method() != null){
            this.method = newPaymentMethodInput.method();
        }
        if (newPaymentMethodInput.discount() != null){
            this.discount = newPaymentMethodInput.discount();
        }
    }

    public void disable() {
        this.active = false;
    }
}
