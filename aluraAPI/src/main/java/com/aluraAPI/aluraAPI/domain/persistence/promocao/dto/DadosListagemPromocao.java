package com.aluraAPI.aluraAPI.domain.persistence.promocao.dto;

import com.aluraAPI.aluraAPI.domain.persistence.promocao.Promocao;

import java.util.Date;

public record DadosListagemPromocao(
        Long id,
        String nome,
        Date validade
) {
    public DadosListagemPromocao(Promocao promocao) {
        this(promocao.getId(),
                promocao.getNome(),
                promocao.getValidade()
        );
    }
}
