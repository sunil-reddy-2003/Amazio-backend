package com.ecommerce.amazio.service;


import com.ecommerce.amazio.exceptions.ProductNotFoundException;
import com.ecommerce.amazio.model.Product;
import com.ecommerce.amazio.repository.ProductRepo;
import com.ecommerce.amazio.requestDto.ProductRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProductService {

    ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(ProductRequestDto product) {
        Product saveProduct=new Product();
        saveProduct.setName(product.getName());
        saveProduct.setImageUrl(product.getImageUrl());
        saveProduct.setPrice(product.getPrice());
        saveProduct.setDescription(product.getDescription());
        saveProduct.setCategory(product.getCategory());
        saveProduct.setCreatedAt(LocalDateTime.now());
        saveProduct.setUpdatedAt(LocalDateTime.now());
        return productRepo.save(saveProduct);
    }
    public void deleteProduct(int id){
        if(!productRepo.existsById(id)){
            throw new ProductNotFoundException("NO PRODUCT WITH ID "+id);
        }
        productRepo.deleteById(id);
    }

    public Product  updateProduct(int id, Product product) {
        if(!productRepo.existsById(id)){
            throw new ProductNotFoundException("PRODUCT NOT FOUND WITH ID: "+id);
        }
        Product oldProduct = productRepo.getReferenceById(id);
        oldProduct.setName(product.getName());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setImageUrl(product.getImageUrl());
        oldProduct.setUpdatedAt(LocalDateTime.now());
        return productRepo.save(oldProduct);
    }

    public Page<Product> getAllProducts(int page, int size,List<String> category) {
//        return productRepo.findAll(PageRequest.of(page,size, Sort.by("price").ascending())).getContent();
        Pageable pageable= PageRequest.of(page,size, Sort.by("price").ascending());
        if(category == null || category.isEmpty()) return productRepo.findAll(pageable);

        return productRepo.findByCategoryIn(category,pageable);
    }
}
