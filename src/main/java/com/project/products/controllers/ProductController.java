package com.project.products.controllers;

import com.project.products.dtos.ProductDto;
import com.project.products.models.Product;
import com.project.products.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    // o @Valid é necessário em conjunto com o @NotBlank e @NotNull nos records...
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto){
        var product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> listProducts = repository.findAll();
        if(!listProducts.isEmpty()){
            for(Product product : listProducts){
                // Esta linha poderia ser usada no método abaixo "getOneProductById"
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProductById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProductById(@PathVariable(value="id") UUID id){
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        obj.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(obj.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductDto productDto){
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productUpdated = obj.get();
        BeanUtils.copyProperties(productDto, productUpdated);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(productUpdated));
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        repository.delete(obj.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
