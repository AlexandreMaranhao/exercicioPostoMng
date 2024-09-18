package com.aluraAPI.aluraAPI.persistence.produto;

import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosAtualizarProduto;
import com.aluraAPI.aluraAPI.persistence.produto.dto.DadosCadastroProduto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private float preco;
    //TODO: mudar todas as FK para long
    private int categoria_id;
    private Date validade;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.categoria_id = dados.categoria_id();
        this.validade = dados.validade();
    }


    public void atualizarDadosProduto(@Valid DadosAtualizarProduto dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.preco() != 0.0f){
            this.preco = dados.preco();
        }
        if (dados.categoria_id() != 0.0d){ //TODO: mudar todas as FK para long
            this.categoria_id = dados.categoria_id();
        }
        if (dados.validade() != null){
            this.validade = dados.validade();
        }


    }
}
