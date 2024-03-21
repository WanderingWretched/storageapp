package com.example.storageapp.service;

import com.example.storageapp.domain.entities.ProductEntity;
import com.example.storageapp.domain.exceptions.ResourceExistException;
import com.example.storageapp.domain.exceptions.ResourceNotFoundException;
import com.example.storageapp.repository.ProductRepository;
import com.example.storageapp.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    public void ProductService_CreateProductReturnProduct() throws ResourceExistException {
        ProductEntity product = ProductEntity.builder()
                .name("First product in service")
                .article("TEST first product article in service")
                .description("TEST first product description in service")
                .category("TEST first product category in service")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        when(repository.save(Mockito.any(ProductEntity.class))).thenReturn(product);

        ProductEntity savedProduct = service.create(product);

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductService_GetProductByID_ReturnProduct() throws ResourceNotFoundException {
        ProductEntity product = ProductEntity.builder()
                .name("Second product in service")
                .article("TEST second product article in service")
                .description("TEST second product description in service")
                .category("TEST second product category in service")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        when(repository.findById(product.getId())).thenReturn(Optional.ofNullable(product));

        ProductEntity savedProduct = service.findProductById(product.getId());

        Assertions.assertThat(savedProduct).isNotNull();
    }

    @Test
    public void ProductService_UpdateProduct_ReturnProduct() throws ResourceNotFoundException {
        ProductEntity product = ProductEntity.builder()
                .name("Third product in service")
                .article("TEST third product article in service")
                .description("TEST third product description in service")
                .category("TEST third product category in service")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        when(repository.findById(product.getId())).thenReturn(Optional.ofNullable(product));
        when(repository.save(Mockito.any(ProductEntity.class))).thenReturn(product);

        boolean savedProduct = service.update(product.getId(), product);

        Assertions.assertThat(savedProduct).isTrue();
    }

    @Test
    public void ProductService_DeleteProduct_ReturnProduct() throws ResourceNotFoundException {
        ProductEntity product = ProductEntity.builder()
                .name("Fourth product in service")
                .article("TEST fourth product article in service")
                .description("TEST fourth product description in service")
                .category("TEST third fourth category in service")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        when(repository.findById(product.getId())).thenReturn(Optional.ofNullable(product));

        assertAll(() -> service.deleteProduct(product.getId()));
    }
}
