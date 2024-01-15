package com.project.products.repositories;

import com.project.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.value BETWEEN :minValue and :maxValue")
    List<Product> findByValueBetween(@Param("minValue") BigDecimal minValue, @Param("maxValue") BigDecimal maxValue);
//    List<Product> findByNomeContaining(String nome);
}
