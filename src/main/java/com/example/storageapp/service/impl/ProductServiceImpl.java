package com.example.storageapp.service.impl;


import com.example.storageapp.domain.exceptions.ResourceExistException;
import com.example.storageapp.domain.exceptions.ResourceNotFoundException;
import com.example.storageapp.repository.ProductRepository;
import com.example.storageapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.storageapp.domain.entities.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Interface implementation with crud methods. Here is the main logic and interaction with the database
 * */

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity create(ProductEntity product) throws ResourceExistException {
        ProductEntity existProduct = productRepository.findProductByArticle(product.getArticle());
        if (existProduct == null) {
            return productRepository.save(product);
        } else {
            throw new ResourceExistException("Продукт с таким Article уже существует");
        }
    }

    @Override
    public boolean update(UUID productId, ProductEntity product) throws ResourceNotFoundException {
        ProductEntity existProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с Id " + productId + " не найден"));
        existProduct.setName(product.getName());
        existProduct.setArticle(product.getArticle());
        existProduct.setDescription(product.getDescription());
        existProduct.setCategory(product.getCategory());
        existProduct.setPrice(product.getPrice());
        existProduct.setCount(product.getCount());
        existProduct.setLastDateUpdatedCount(LocalDateTime.now());
        productRepository.save(existProduct);
        return true;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity findProductById(UUID productId) throws ResourceNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с Id" + productId + " не найден"));
    }

    @Override
    public void deleteProduct(UUID productId) throws ResourceNotFoundException {
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с Id " + productId + " не найден"));
        productRepository.deleteById(existingProduct.getId());
    }
}
