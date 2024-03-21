package com.example.storageapp.service;



import com.example.storageapp.domain.entities.ProductEntity;
import com.example.storageapp.domain.exceptions.ResourceExistException;
import com.example.storageapp.domain.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Interface with crud methods
 */
public interface ProductService {

    ProductEntity create(ProductEntity product) throws ResourceExistException, ResourceNotFoundException;
    boolean update(UUID productId,ProductEntity product) throws ResourceNotFoundException;

    List<ProductEntity> getAllProducts();

    ProductEntity findProductById(UUID productId) throws ResourceNotFoundException;

    void deleteProduct(UUID productId) throws ResourceNotFoundException;
}
