package com.aluraAPI.aluraAPI.domain.persistence.produto.business;

import com.aluraAPI.aluraAPI.domain.persistence.categoria.Categoria;
import com.aluraAPI.aluraAPI.domain.persistence.categoria.CategoriaRepository;
import com.aluraAPI.aluraAPI.domain.persistence.produto.Produto;
import com.aluraAPI.aluraAPI.domain.persistence.produto.ProdutoRepository;
import com.aluraAPI.aluraAPI.domain.persistence.produto.dto.DadosCadastroProduto;
import com.aluraAPI.aluraAPI.exceptions.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarProduto {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public void cadastrarProduto(DadosCadastroProduto dados){
        if(!categoriaRepository.existsById(dados.categoriaId())){
            throw new GeneralException(("Categoria informada não existe"));
        }


        var categoria = categoriaRepository.findById(dados.categoriaId()).get();
        var produto = new Produto(dados.nome(), dados.preco(), categoria);
        produtoRepository.save(produto);
    }
}
