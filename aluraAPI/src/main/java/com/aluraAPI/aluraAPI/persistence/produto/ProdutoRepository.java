package com.aluraAPI.aluraAPI.persistence.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
