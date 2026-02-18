package com.ecommerce.amazio.model;

import com.ecommerce.amazio.enums.PaymentStatus;
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
public class Payment {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = false)
    private double amountPaid;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
