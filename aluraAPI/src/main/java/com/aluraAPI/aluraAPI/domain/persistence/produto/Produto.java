package com.aluraAPI.aluraAPI.domain.persistence.produto;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.Categoria;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosAtualizarProduto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosCadastroProduto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

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
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoriaId;

    @Getter
    private boolean ativo;


    public Produto(String nome, float preco, Categoria categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.categoriaId = categoriaId;
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

    public Categoria getCategoria() {
        return categoriaId;
    }
}
