package com.ecommerce.amazio.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private int orderItemId;

    @Column(nullable = false)
    private int  productId;

    @Column(nullable = false)
    private int quantity;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
