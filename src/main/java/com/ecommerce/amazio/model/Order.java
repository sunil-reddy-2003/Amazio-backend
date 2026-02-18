package com.ecommerce.amazio.model;


import com.ecommerce.amazio.enums.OrderStatus;
import com.ecommerce.amazio.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orderTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @JsonManagedReference
    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Address address;

    @JsonManagedReference
    @OneToOne(mappedBy ="order",cascade = CascadeType.ALL)
    private Payment payment;

    @JsonBackReference
    @JoinColumn(name = "user_Id")
    @ManyToOne
    private User user;
}
