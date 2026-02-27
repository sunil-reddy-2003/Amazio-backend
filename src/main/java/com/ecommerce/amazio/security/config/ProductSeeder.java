package com.ecommerce.amazio.security.config;


import com.ecommerce.amazio.model.Product;
import com.ecommerce.amazio.repository.ProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ProductSeeder {
    @Bean
    CommandLineRunner seedProducts(ProductRepo productRepository) {
        return args -> {

            System.out.println("Product count = " + productRepository.count());

            if (productRepository.count() == 0) {

                ObjectMapper mapper = new ObjectMapper();
                InputStream inputStream =
                        new ClassPathResource("products.json").getInputStream();

                List<Product> products =
                        mapper.readValue(inputStream, new TypeReference<List<Product>>() {});

                for (Product product : products) {
                    product.setCreatedAt(LocalDateTime.now());
                    product.setUpdatedAt(LocalDateTime.now());
                }

                productRepository.saveAll(products);

                System.out.println("🔥 All products seeded successfully.");
            }
        };
    }
}