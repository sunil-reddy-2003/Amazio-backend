package com.ecommerce.amazio.convertors;

import com.ecommerce.amazio.model.Order;
import com.ecommerce.amazio.requestDto.OrderRequestDto;

public class OrderConvertor {
    public static Order convertOrderRequestInToOrderModel(OrderRequestDto orderRequestDto){
        Order order=new Order();
        order.setOrderItems(orderRequestDto.getOrderItem());
        order.setAddress(orderRequestDto.getAddress());
        return order;
    }
}
