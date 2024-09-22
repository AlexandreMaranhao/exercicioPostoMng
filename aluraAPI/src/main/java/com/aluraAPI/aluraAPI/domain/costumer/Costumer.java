package com.aluraAPI.aluraAPI.domain.costumer;

//import com.aluraAPI.aluraAPI.domain.persistence.cliente.dto.DadosAtualizaCliente;
import com.aluraAPI.aluraAPI.domain.costumer.dto.UpdateCostumer;
import com.aluraAPI.aluraAPI.domain.costumer.dto.RegisterCostumer;
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


    public Costumer(@Valid RegisterCostumer newCostumerInput) {
        this.cpf = newCostumerInput.cpf();
        this.name = newCostumerInput.name();
        this.active = true;
    }


    public void updateCostumer(@Valid UpdateCostumer newInput) { //TODO: alteracao so permite inserir fidelidade
        if (newInput.name() != null){
            this.name = newInput.name();
        }
        if (newInput.cpf() != null){
            this.cpf = newInput.cpf();
        }
    }

    public void disable() {this.active = false;}

}
