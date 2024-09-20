package com.aluraAPI.aluraAPI.domain.persistence.promocao;


import com.aluraAPI.aluraAPI.domain.persistence.promocao.dto.DadosAtualizaPromocao;
import com.aluraAPI.aluraAPI.domain.persistence.promocao.dto.DadosCadastroPromocao;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;

@Table(name = "promocao")
@Entity(name = "Promocao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Date validade;
    private boolean ativo;


    public Promocao(@Valid DadosCadastroPromocao dados) {
        this.nome = dados.nome();
        this.validade = dados.validade();
        this.ativo = true;
    }


    public void atualizarDadosPromocao(@Valid DadosAtualizaPromocao dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.validade() != null){
            this.validade = dados.validade();
        }

    }

    public void inativar() {this.ativo = false;}
}
