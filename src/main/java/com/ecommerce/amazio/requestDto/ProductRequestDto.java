package com.ecommerce.amazio.requestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private String  imageUrl;
    private double price;
    private String description;
    private String category;

}
