package com.aluraAPI.aluraAPI.domain.deal;


import com.aluraAPI.aluraAPI.domain.deal.dto.DealUpdateDto;
import com.aluraAPI.aluraAPI.domain.deal.dto.DealRegisterDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Table(name = "deal")
@Entity(name = "Deal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date validity;
    private boolean active;


    public Deal(@Valid DealRegisterDto dados) {
        this.name = dados.name();
        this.validity = dados.validity();
        this.active = true;
    }


    public void updateDeal(@Valid DealUpdateDto newDealInput) {
        if (newDealInput.name() != null){
            this.name = newDealInput.name();
        }
        if (newDealInput.validity() != null){
            this.validity = newDealInput.validity();
        }

    }

    public void disable() {this.active = false;}
}
