package com.aluraAPI.aluraAPI.domain.loyalty;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyUpdateDto;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.LoyaltyRegisterDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "loyalty")
@Entity(name = "Loyalty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private int points;
    private boolean active;


    public Loyalty(@Valid LoyaltyRegisterDto registerLoyaltyInput) {
        this.number = registerLoyaltyInput.number();
        this.active = true;
        this.points = 0;
    }

    public Loyalty(String loyaltyNumber, int points) {
        this.number = loyaltyNumber;
        this.active = true;
        this.points = points;
    }


    public void updateLoyalty(@Valid LoyaltyUpdateDto updateLoyaltyInput) {
        if (updateLoyaltyInput.number() != null){
            this.number = updateLoyaltyInput.number();
        }
        if (updateLoyaltyInput.points() != 0.0d){
            this.points = updateLoyaltyInput.points();
        }
    }

    public void disable() {this.active = false;}

}
