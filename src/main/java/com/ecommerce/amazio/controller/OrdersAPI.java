package com.ecommerce.amazio.controller;

import com.ecommerce.amazio.model.Address;
import com.ecommerce.amazio.model.Order;
import com.ecommerce.amazio.model.OrderItem;
import com.ecommerce.amazio.requestDto.OrderInfoResponseDto;
import com.ecommerce.amazio.requestDto.OrderRequestDto;
import com.ecommerce.amazio.requestDto.OrdersResponseDto;
import com.ecommerce.amazio.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:5173")
public class OrdersAPI {

    OrderService orderService;

    @Autowired
    public OrdersAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto){

        log.info("inside createOrder with "+orderRequestDto.toString());

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();

        List<OrderItem> orderItem=orderRequestDto.getOrderItem();
        Address address=orderRequestDto.getAddress();
        String paymentMethod=orderRequestDto.getPaymentMethod();

        Order order= orderService.createOrder(orderItem,address,paymentMethod,email);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getOrders/{userId}")
    public ResponseEntity<?> getOrdersWithUserId(@PathVariable UUID userId){
       List<OrdersResponseDto> orders=orderService.getOrdersWithUserId(userId);
       return ResponseEntity.ok(orders);
    }
    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable UUID orderId){
        OrderInfoResponseDto order=orderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(order);
    }
}