package com.aluraAPI.aluraAPI.domain.costumer;

//import com.aluraAPI.aluraAPI.domain.persistence.cliente.dto.DadosAtualizaCliente;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerUpdateDto;
import com.aluraAPI.aluraAPI.domain.costumer.dto.CostumerRegisterDto;
import com.aluraAPI.aluraAPI.domain.loyalty.Loyalty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "costumer")
@Entity(name = "Costumer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cpf;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_id", referencedColumnName = "id")
    private Loyalty loyaltyId;

    private boolean active;


    public Costumer(@Valid CostumerRegisterDto newCostumerInput) {
        this.cpf = newCostumerInput.cpf();
        this.name = newCostumerInput.name();
        this.active = true;
    }

    public Costumer(Costumer newRegistredCostumer, Loyalty loyalty) {
        this.cpf = newRegistredCostumer.getCpf();
        this.name = newRegistredCostumer.getName();
        this.loyaltyId = loyalty;
        this.active = true;
    }


    public void updateCostumer(@Valid CostumerUpdateDto updateCostumerInput) { //TODO: alteracao so permite inserir fidelidade
        if (updateCostumerInput.name() != null){
            this.name = updateCostumerInput.name();
        }
        if (updateCostumerInput.cpf() != null){
            this.cpf = updateCostumerInput.cpf();
        }
    }

    public void disable() {this.active = false;}

}
