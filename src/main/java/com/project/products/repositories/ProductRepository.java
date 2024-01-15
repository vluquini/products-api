package com.project.products.repositories;

import com.project.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
//    List<Product> findByNomeContaining(String nome);
}
