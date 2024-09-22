package com.aluraAPI.aluraAPI.domain.loyalty;

import com.aluraAPI.aluraAPI.domain.loyalty.dto.UpdateLoyalty;
import com.aluraAPI.aluraAPI.domain.loyalty.dto.RegisterLoyalty;
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


    public Loyalty(@Valid RegisterLoyalty newLoyaltyInput) {
        this.number = newLoyaltyInput.number();
        this.active = true;
        this.points = 0;
    }


    public void updateLoyalty(@Valid UpdateLoyalty newLoyaltyInput) {
        if (newLoyaltyInput.number() != null){
            this.number = newLoyaltyInput.number();
        }
        if (newLoyaltyInput.points() != 0.0d){
            this.points = newLoyaltyInput.points();
        }
    }

    public void disable() {this.active = false;}

}
