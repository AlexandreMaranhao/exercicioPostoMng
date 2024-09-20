package com.aluraAPI.aluraAPI.domain.persistence.produto.dto;

import com.aluraAPI.aluraAPI.domain.persistence.produto.Produto;

public record DadosListagemProduto(
        long id,
        String nome,
        float preco,
        long categoriaId,//TODO: Mudar modo de chamada da FK
        boolean ativo
)
{

    public DadosListagemProduto(Produto produto){
        this(produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria().getId(), // Obtendo o ID da categoria
                produto.isAtivo()
        );
    }

}
