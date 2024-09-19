package com.aluraAPI.aluraAPI.persistence.produto.dto;

import com.aluraAPI.aluraAPI.persistence.produto.Produto;

import java.util.Date;

public record DadosListagemProduto(
        long id,
        String nome,
        float preco,
        long categoria_id,//TODO: Mudar modo de chamada da FK
        boolean ativo
)
{

    public DadosListagemProduto(Produto produto){
        this(produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria_id(),
                produto.isAtivo()
        );
    }

}
