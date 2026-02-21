package com.ecommerce.amazio.repository;


import com.ecommerce.amazio.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {


    Page<Product> findByNameContainingIgnoreCaseAndCategoryIn(
            String name,
            List<String> category,
            Pageable pageable
    );

    Page<Product> findByNameContainingIgnoreCase(String s, Pageable pageable);
}
