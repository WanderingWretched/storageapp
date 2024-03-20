package com.example.storageapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Поле обязательно для заполнения")
    private String name;

    @Column(unique = true, name = "article")
    @NotBlank(message = "Поле обязательно для заполнения")
    private String article;

    @NotBlank(message = "Поле обязательно для заполнения")
    private String description;

    @NotBlank(message = "Поле обязательно для заполнения")
    private String category;

    @NotNull
    @Min(value = 1, message = "Поле обязательно для заполнения,и не может быть меньше 1")
    private double price;

    @NotNull
    @Min(value = 1, message = "Поле обязательно для заполнения,и не может быть меньше 1")
    private int count;

    @UpdateTimestamp
    private LocalDateTime lastDateUpdatedCount;
}
