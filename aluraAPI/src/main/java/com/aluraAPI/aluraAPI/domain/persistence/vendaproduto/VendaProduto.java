package com.aluraAPI.aluraAPI.domain.persistence.vendaproduto;

import com.aluraAPI.aluraAPI.domain.persistence.vendaproduto.dto.DadosCadastroVendaProduto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "vendaProduto")
@Entity(name = "VendaProduto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VendaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float quantidade;
    private long vendasId;
    private long produtoId;


    public VendaProduto(@Valid DadosCadastroVendaProduto dados) {
        this.vendasId = dados.vendasId();
        this.produtoId = dados.produtoId();
        this.quantidade = dados.quantidade();
    }


}
