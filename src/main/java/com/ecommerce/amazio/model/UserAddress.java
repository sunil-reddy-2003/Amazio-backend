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
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private int addressId;
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
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}

