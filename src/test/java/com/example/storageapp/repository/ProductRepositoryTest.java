package com.example.storageapp.repository;

import com.example.storageapp.domain.entities.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    public void ProductRepository_Save_ReturnSavedProducts() {
        ProductEntity product = ProductEntity.builder()
                .name("First product")
                .article("TEST first product article")
                .description("TEST first product description")
                .category("TEST first product category")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        ProductEntity savedProduct = repository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getCount()).isGreaterThan(1);
    }

    @Test
    public void ProductRepository_FindById_ReturnProductNotNull() {
        ProductEntity product = ProductEntity.builder()
                .name("Fourth product")
                .article("TEST fourth product article")
                .description("TEST Fourth product description")
                .category("TEST Fourth product category")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();
        repository.save(product);

        ProductEntity existProduct = repository.findById(product.getId()).get();

        Assertions.assertThat(existProduct).isNotNull();
    }

    @Test
    public void ProductRepository_FindByProductArticle_ReturnProductNotNull() {
        ProductEntity product = ProductEntity.builder()
                .name("Fifth product")
                .article("TEST fifth product article")
                .description("TEST fifth product description")
                .category("TEST fifth product category")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();
        repository.save(product);

        ProductEntity existProduct = repository.findProductByArticle(product.getArticle());

        Assertions.assertThat(existProduct).isNotNull();
    }

    @Test
    public void ProductRepository_UpdateProduct_ReturnProduct() {
        ProductEntity product = ProductEntity.builder()
                .name("Fifth product")
                .article("TEST fifth product article")
                .description("TEST fifth product description")
                .category("TEST fifth product category")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();

        repository.save(product);

        ProductEntity existProduct = repository.findById(product.getId()).get();
        existProduct.setName("Fifth product update");
        existProduct.setArticle("TEST fifth product article update");
        existProduct.setDescription("TEST fifth product description update");
        existProduct.setCategory("TEST fifth product category update");
        existProduct.setPrice(101);
        existProduct.setCount(3);

        ProductEntity updatedProduct = repository.save(existProduct);

        Assertions.assertThat(updatedProduct.getName()).isNotNull();
        Assertions.assertThat(updatedProduct.getArticle()).isNotNull();
    }

    @Test
    public void ProductRepository_DeleteProduct_ReturnProductIsEmpty() {
        ProductEntity product = ProductEntity.builder()
                .name("Delete product")
                .article("TEST Delete product")
                .description("TEST Delete product")
                .category("TEST Delete product")
                .price(100)
                .count(2)
                .lastDateUpdatedCount(LocalDateTime.now())
                .build();
        repository.save(product);

        repository.deleteById(product.getId());

        Optional<ProductEntity> deletedProduct = repository.findById(product.getId());

        Assertions.assertThat(deletedProduct).isEmpty();
    }
}
