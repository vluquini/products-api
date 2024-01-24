package com.project.products.services;

import com.project.products.controllers.ProductController;
import com.project.products.dtos.ProductDto;
import com.project.products.exceptions.NoProductsFoundException;
import com.project.products.exceptions.ProductNotFoundException;
import com.project.products.models.Product;
import com.project.products.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ResponseEntity<Product> saveProduct(ProductDto productDto){
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }

    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        Page<Product> listProducts = repository.findAll(pageable);
        if(listProducts.isEmpty()){
            throw new NoProductsFoundException();
        }
        for(Product product : listProducts){
            // Esta linha poderia ser usada no método abaixo "getOneProductById"
            UUID id = product.getIdProduct();
            product.add(linkTo(methodOn(ProductController.class).getOneProductById(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    public ResponseEntity<Object> getOneProductById(UUID id){
        Optional<Product> obj = repository.findById(id);
//        Pageable defaultPageable = PageRequest.of(0, 10);
        if (obj.isEmpty()){
            throw new ProductNotFoundException();
        }
        obj.get().add(linkTo(methodOn(ProductController.class).getAllProducts(null)).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(obj.get());
    }

    public ResponseEntity<Page<Product>> getProductsByName(String name, Pageable pageable) {
        Page<Product> listProducts = repository.findByNameContaining(name, pageable);
        if (listProducts.isEmpty()){
            throw new NoProductsFoundException();
        }
        for(Product product : listProducts){
            // Esta linha poderia ser usada no método abaixo "getOneProductById"
            UUID id = product.getIdProduct();
            product.add(linkTo(methodOn(ProductController.class).getOneProductById(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    public ResponseEntity<Page<Product>> getProductsByValueRange(BigDecimal minValue, BigDecimal maxValue, Pageable pageable) {
        Page<Product> listProducts = repository.findByValueBetween(minValue, maxValue, pageable);
        if(listProducts.isEmpty()) {
            throw new NoProductsFoundException();
        }
        for(Product product : listProducts){
            UUID id = product.getIdProduct();
            product.add(linkTo(methodOn(ProductController.class).getOneProductById(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    public ResponseEntity<Object> updateProduct(UUID id, ProductDto productDto){
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()){
            throw new ProductNotFoundException();
        }
        var productUpdated = obj.get();
        BeanUtils.copyProperties(productDto, productUpdated);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(productUpdated));
    }

    public ResponseEntity<Object> deleteProduct(UUID id){
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()){
            throw new ProductNotFoundException();
        }
        repository.delete(obj.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
//    public ResponseEntity<List<Product>> getProductsByValueRange(BigDecimal minValue, BigDecimal maxValue) {
//        List<Product> listProducts = repository.findByValueBetween(minValue, maxValue);
//        if(listProducts.isEmpty()) {
//            throw new NoProductsFoundException();
//        }
//        for(Product product : listProducts){
//            // Esta linha poderia ser usada no método abaixo "getOneProductById"
//            UUID id = product.getIdProduct();
//            product.add(linkTo(methodOn(ProductController.class).getOneProductById(id)).withSelfRel());
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
//    }
}
