package com.ecommerce.amazio.requestDto;

import com.ecommerce.amazio.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResponseDto {
    OrderStatus orderStatus;
    String date;
    double totalPrice;
    int totalQuantity;
    List<OrderItemResponseDto> orderItems;
    AddressResponseDto address;
    PaymentResponseDto payment;

}
