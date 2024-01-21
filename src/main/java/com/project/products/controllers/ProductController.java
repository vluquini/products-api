package com.project.products.controllers;

import com.project.products.dtos.ProductDto;
import com.project.products.models.Product;
import com.project.products.repositories.ProductRepository;
import com.project.products.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Tag(name = "Products Controller")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductService service;

    // o @Valid é necessário em conjunto com o @NotBlank e @NotNull nos records...
    @Operation(summary = "Create product", description = "Cria um produto no Banco de Dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
    })
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDto productDto){
        return service.saveProduct(productDto);
    }

    @Operation(summary = "Get all products", description = "Retorna uma lista com todos os produtos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado"),
    })
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        return service.getAllProducts(pageable);
    }

    @Operation(summary = "Get all products by value filter", description = "Busca produtos dentro de uma faixa de valor", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "404", description = "Não há produtos nesta faixa de valor"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
    })
    @GetMapping("/filtered")
    public ResponseEntity<List<Product>> getAllProductsFiltered(@RequestParam(name = "minValue", required = false) BigDecimal minValue,
                                                                @RequestParam(name = "maxValue", required = false) BigDecimal maxValue){
        return service.getAllProductsFiltered(minValue, maxValue);
    }

    @Operation(summary = "Get one product by ID", description = "Busca um produto pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProductById(@PathVariable(value="id") UUID id){
        return service.getOneProductById(id);
    }

    @Operation(summary = "Update product by ID", description = "Atualiza um produto pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto atualizado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductDto productDto){
        return service.updateProduct(id, productDto);
    }

    @Operation(summary = "Delete product by ID",description = "Apaga um produto pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto apagado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        return service.deleteProduct(id);
    }

}
