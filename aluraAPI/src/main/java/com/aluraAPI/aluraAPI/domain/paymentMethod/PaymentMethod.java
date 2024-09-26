package com.aluraAPI.aluraAPI.domain.paymentMethod;

import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.PaymentMethodUpdateDto;
import com.aluraAPI.aluraAPI.domain.paymentMethod.dto.PaymentMethodRegisterDto;
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

    public PaymentMethod(PaymentMethodRegisterDto registerPaymentMethodInput) {
        this.method = registerPaymentMethodInput.method();
        this.discount = registerPaymentMethodInput.discount();
        this.active = true;
    }



    public void updatePaymentMethod(@Valid PaymentMethodUpdateDto updatePaymentMethodInput) {
        if (updatePaymentMethodInput.method() != null){
            this.method = updatePaymentMethodInput.method();
        }
        if (updatePaymentMethodInput.discount() != null){
            this.discount = updatePaymentMethodInput.discount();
        }
    }

    public void disable() {
        this.active = false;
    }
}
