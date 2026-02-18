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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private int addressId;
    @Column(name = "receiverName", nullable = false)
    private String name;
    @Column(name = "receiverMobile", nullable = false)
    private long mobile;
    @Column( nullable = false)
    private int pincode;
    @Column(nullable = false)
    private String area;
    @Column(nullable = false)
    private String flat;
    @Column(nullable = false)
    private String landmark;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String addressType;
    @Column(nullable = false,name = "is_default")
    private boolean defaultAddress;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;


    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
