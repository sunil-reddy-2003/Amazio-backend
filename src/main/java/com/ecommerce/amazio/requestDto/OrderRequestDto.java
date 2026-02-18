package com.ecommerce.amazio.requestDto;


import com.ecommerce.amazio.model.Address;
import com.ecommerce.amazio.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private List<OrderItem> orderItem;
    private Address address;
    private String paymentMethod;
}
