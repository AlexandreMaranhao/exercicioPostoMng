package com.aluraAPI.aluraAPI.controller;


import com.aluraAPI.aluraAPI.domain.saleProduct.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vendaproduto")
public class SaleProductController {

    @Autowired
    private SaleProductRepository saleProductRepository;
/*
    @PostMapping
    @Transactional
    public void cadastrarVendaProduto(@RequestBody @Valid DadosCadastroVendaProduto dados){
        vendaProdutoRepository.save(new VendaProduto(dados));
    }

    @GetMapping
    public List<DadosListagemVendaProduto> listarVendaProduto(){
        return vendaProdutoRepository.findAll().stream().map(DadosListagemVendaProduto::new).toList();
    }
*/

}

