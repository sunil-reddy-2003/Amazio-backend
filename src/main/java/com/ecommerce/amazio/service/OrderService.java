package com.ecommerce.amazio.service;


import com.ecommerce.amazio.enums.OrderStatus;
import com.ecommerce.amazio.enums.PaymentStatus;
import com.ecommerce.amazio.model.*;
import com.ecommerce.amazio.repository.OrderRepo;
import com.ecommerce.amazio.repository.ProductRepo;
import com.ecommerce.amazio.repository.UserRepo;
import com.ecommerce.amazio.requestDto.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    OrderRepo orderRepo;
    ProductRepo productRepo;
    UserRepo userRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo,ProductRepo productRepo,UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.productRepo=productRepo;
        this.userRepo=userRepo;
    }

    public Order createOrder(List<OrderItem> orderItem, Address address,String paymentMethod,String email) {

        Order order=new Order();

        double totalPrice=0;
        int totalQuantity=0;
        List<OrderItem> items=new ArrayList<>();

        for(OrderItem item : orderItem){
            item.setOrder(order);
            items.add(item);
            Product product= productRepo.findById(item.getProductId()).get();
            totalPrice+=product.getPrice()*item.getQuantity();
            totalQuantity+=item.getQuantity();
        }


        Address currAddress=new Address();

        currAddress.setName(address.getName());
        currAddress.setMobile(address.getMobile());
        currAddress.setPincode(address.getPincode());
        currAddress.setArea(address.getArea());
        currAddress.setFlat(address.getFlat());
        currAddress.setLandmark(address.getLandmark());
        currAddress.setCity(address.getCity());
        currAddress.setState(address.getState());
        currAddress.setAddressType(address.getAddressType());
        currAddress.setDefaultAddress(address.isDefaultAddress());
        currAddress.setCreatedAt(LocalDateTime.now());
        currAddress.setUpdatedAt(LocalDateTime.now());

        currAddress.setOrder(order);

        Payment payment=new Payment();
        payment.setPaymentStatus(PaymentStatus.PENDING);
        if(paymentMethod.equals("Cash on Delivery")){
            payment.setAmountPaid(0);
            payment.setTransactionId(null);
        }else{
            payment.setAmountPaid(totalPrice);
            payment.setTransactionId(paymentMethod+(int)Math.floor(1000+Math.random()*9000));
        }
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());


        order.setOrderStatus(OrderStatus.PLACED);
        order.setDate(LocalDate.now().toString());
        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);
        order.setAddress(currAddress);
        order.setOrderItems(items);
        order.setPayment(payment);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        User user=userRepo.getUserByEmail(email);
        List<Order> orders = user.getOrders();
        if (orders == null) orders = new ArrayList<>();
        orders.add(order);
        user.setOrders(orders);


        order.setUser(user);

        return orderRepo.save(order);
    }

    public List<OrdersResponseDto> getOrdersWithUserId(UUID userId) {
        User user=userRepo.findById(userId).get();
        List<Order> orders=orderRepo.findByUser(user);
        List<OrdersResponseDto> ordersRes=new ArrayList<>();
        for (Order order:orders){
            ordersRes.add(new OrdersResponseDto(
                    order.getOrderId(),
                    order.getOrderStatus(),
                    order.getDate(),
                    order.getTotalPrice(),
                    order.getTotalQuantity()));
        }
        return ordersRes;
    }


    public OrderInfoResponseDto getOrderByOrderId(UUID orderId) {
        Order order=orderRepo.findById(orderId).get();

        List<OrderItemResponseDto> orderItemResponseDtos=new ArrayList<>();

        List<OrderItem> orderItems=order.getOrderItems();
        for(OrderItem item : orderItems){
            OrderItemResponseDto orderItemResponseDto=new OrderItemResponseDto(
                    item.getProductId(),
                    item.getQuantity(),
                    productRepo.findById(item.getProductId()).get().getImageUrl(),
                    productRepo.findById(item.getProductId()).get().getPrice()
            );

            orderItemResponseDtos.add(orderItemResponseDto);
        }

        Address address=order.getAddress();
        AddressResponseDto addressResponseDto=new AddressResponseDto(
                address.getPincode(),
                address.getArea(),
                address.getFlat(),
                address.getLandmark(),
                address.getCity(),
                address.getState(),
                address.getAddressType(),
                address.isDefaultAddress());



        Payment payment=order.getPayment();
        PaymentResponseDto paymentResponseDto=new PaymentResponseDto(
                payment.getPaymentStatus(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getAmountPaid()
        );


        OrderInfoResponseDto orderInfoResponseDto=new OrderInfoResponseDto(
                order.getOrderStatus(),
                order.getDate(),
                order.getTotalPrice(),
                order.getTotalQuantity(),
                orderItemResponseDtos,
                addressResponseDto,
                paymentResponseDto
        );

        return orderInfoResponseDto;
    }
}
