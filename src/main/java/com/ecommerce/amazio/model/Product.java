package com.ecommerce.amazio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="prodId",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prodName",nullable = false)
    private String name;

    @Column(name = "prodImageUrl",nullable = false,unique = true)
    private String  imageUrl;

    @Column(name = "prodPrice",nullable = false)
    private double price;

    @Column(name = "prodDesc",nullable = false)
    private String description;

    @Column(name = "prodCategory",nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;



}
