package com.aluraAPI.aluraAPI.domain.persistence.categoria;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.dto.DadosAtualizaCategoria;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.dto.DadosCadastroCategoria;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "categoria")
@Entity(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private boolean ativo;


    public Categoria(@Valid DadosCadastroCategoria dados) {
        this.nome = dados.nome();
        this.ativo = true;
    }


    public void atualizarDadosCategoria(@Valid DadosAtualizaCategoria dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
    }

    public void inativar() {this.ativo = false;}

}
