package com.example.storageapp.web.controller;

import com.example.storageapp.domain.entities.ProductEntity;
import com.example.storageapp.domain.exceptions.ResourceExistException;
import com.example.storageapp.domain.exceptions.ResourceNotFoundException;
import com.example.storageapp.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ProductEntity createProduct(@Valid @RequestBody ProductEntity product) throws ResourceExistException, ResourceNotFoundException {
        return productService.create(product);
    }

    @GetMapping("/all")
    public List<ProductEntity> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductEntity getProduct(@PathVariable UUID productId) throws ResourceNotFoundException {
        return productService.findProductById(productId);
    }

    @DeleteMapping("/{productId}")
    void deleteProduct(@PathVariable UUID productId) throws ResourceNotFoundException {
        productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}")
    public boolean updateProduct(@PathVariable UUID productId, @Valid @RequestBody ProductEntity product) throws ResourceNotFoundException {
        return productService.update(productId, product);
    }
}
