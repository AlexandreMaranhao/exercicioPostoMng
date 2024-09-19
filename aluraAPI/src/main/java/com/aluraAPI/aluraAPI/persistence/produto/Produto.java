package com.aluraAPI.aluraAPI.persistence.produto;

import com.aluraAPI.aluraAPI.persistence.categoria.Categoria;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private boolean ativo;


    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.ativo = true;
    }


    public void atualizarDadosProduto(@Valid DadosAtualizarProduto dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.preco() != 0.0f){
            this.preco = dados.preco();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public boolean isAtivo() {return ativo;}




}
