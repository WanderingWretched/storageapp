package com.example.storageapp.repository;


import com.example.storageapp.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findById(UUID productId);

    ProductEntity findProductByArticle(String article);

}
