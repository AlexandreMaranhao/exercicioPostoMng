package com.aluraAPI.aluraAPI.persistence.produto.dto;

import com.aluraAPI.aluraAPI.persistence.produto.Produto;

import java.util.Date;

public record DadosListagemProduto(
        long id,
        String nome,
        float preco,
        int categoria_id,//TODO: Mudar modo de chamada da FK
        Date validade) {

    public DadosListagemProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco() ,produto.getCategoria_id(), produto.getValidade());
    }

}
