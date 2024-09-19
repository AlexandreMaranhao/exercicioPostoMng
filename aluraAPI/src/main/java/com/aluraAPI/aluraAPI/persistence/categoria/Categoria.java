package com.aluraAPI.aluraAPI.persistence.categoria;

import com.aluraAPI.aluraAPI.persistence.categoria.dto.DadosCadastroCategoria;
import com.aluraAPI.aluraAPI.persistence.produto.Produto;
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


}
