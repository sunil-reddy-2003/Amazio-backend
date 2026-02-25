package com.ecommerce.amazio.controller;


import com.ecommerce.amazio.model.Product;
import com.ecommerce.amazio.requestDto.ProductRequestDto;
import com.ecommerce.amazio.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductsAPI {

    ProductService productService;
    @Autowired
    public ProductsAPI(ProductService productService) {
        this.productService=productService;
    }

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDto product) {
        log.info("inside add product: "+product.toString());
        Product dbProduct= productService.addProduct(product);
        return ResponseEntity.ok(dbProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("PRODUCT DELETED SUCCESSFULLY");
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                @RequestBody Product product) {

        Product dbProduct=productService.updateProduct(id,product);
        return ResponseEntity.ok(dbProduct);
    }

    @GetMapping("/getAllProducts")
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "12") int size,
                                        @RequestParam(required = false) String search,
                                        @RequestParam(required = false) List<String> category){

        return productService.getAllProducts(page,size,search,category);
    }

    @GetMapping("/viewallproducts")
    public Page<Product> viewAllProducts(@RequestParam(defaultValue = "0")int page,
                                         @RequestParam(defaultValue = "12")int size,
                                         @RequestParam(required = false)String search,
                                         @RequestParam(required = false)List<String> category){
        return productService.viewAllProducts(page,size,search,category);
    }

    @GetMapping("/getproductbyid/{prodId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProductById(@PathVariable int prodId){
        Product product=productService.getProductById(prodId);
        return ResponseEntity.ok(product);
    }
}
