package com.ecommerce.amazio.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    int pincode;
    String area;
    String flat;
    String landmark;
    String city;
    String state;
    String addressType;
    boolean defaultAddress;
}
