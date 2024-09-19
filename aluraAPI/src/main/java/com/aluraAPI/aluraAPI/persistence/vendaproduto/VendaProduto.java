package com.aluraAPI.aluraAPI.persistence.vendaproduto;

import com.aluraAPI.aluraAPI.persistence.vendaproduto.dto.DadosCadastroVendaProduto;
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
    private long vendas_id;
    private long produto_id;


    public VendaProduto(@Valid DadosCadastroVendaProduto dados) {
        this.vendas_id = dados.vendas_id();
        this.produto_id = dados.produto_id();
        this.quantidade = dados.quantidade();
    }


}
