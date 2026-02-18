package com.ecommerce.amazio.requestDto;

import com.ecommerce.amazio.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDto {
    UUID orderId;
    OrderStatus orderStatus;
    String date;
    double totalPrice;
    int totalQuantity;
}
