package com.ecommerce.amazio.convertors;

import com.ecommerce.amazio.model.Product;
import com.ecommerce.amazio.requestDto.ProductRequestDto;

public class ProductConvertor {
    public static Product convertProductRequestInToProductModel(ProductRequestDto productRequestDto){
        Product product=new Product();
        product.setName(productRequestDto.getName());
        product.setImageUrl(product.getImageUrl());
        product.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        product.setCategory(productRequestDto.getCategory());
        return product;
    }
}