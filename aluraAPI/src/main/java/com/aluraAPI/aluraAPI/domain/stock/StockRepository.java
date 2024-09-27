package com.aluraAPI.aluraAPI.domain.stock;

import com.aluraAPI.aluraAPI.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {


    List<Stock> findByProductId(@NotNull Product productId);
}
