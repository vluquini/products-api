package com.project.products.controllers;

import com.project.products.dtos.ProductDto;
import com.project.products.models.Product;
import com.project.products.repositories.ProductRepository;
import com.project.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductService service;

    // o @Valid é necessário em conjunto com o @NotBlank e @NotNull nos records...
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto){
        return service.saveProduct(productDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/products/filtered")
    public ResponseEntity<List<Product>> getAllProductsFiltered(@RequestParam(name = "minValue", required = false) BigDecimal minValue,
                                                                @RequestParam(name = "maxValue", required = false) BigDecimal maxValue){
        return service.getAllProductsFiltered(minValue, maxValue);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProductById(@PathVariable(value="id") UUID id){
        return service.getOneProductById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductDto productDto){
        return service.updateProduct(id, productDto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        return service.deleteProduct(id);
    }

}
