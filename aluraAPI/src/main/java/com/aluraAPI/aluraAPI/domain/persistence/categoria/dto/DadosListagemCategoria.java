package com.aluraAPI.aluraAPI.domain.persistence.categoria.dto;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.Categoria;

public record DadosListagemCategoria(
        long id,
        String nome
) {
    public DadosListagemCategoria(Categoria categoria) {
        this(categoria.getId(),
                categoria.getNome()
        );
    }
}
