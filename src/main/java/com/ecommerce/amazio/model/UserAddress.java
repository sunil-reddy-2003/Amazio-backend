package com.ecommerce.amazio.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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
    @Column(name = "receiverName", nullable = false)
    private String name;
    @Column(name = "receiverMobile", nullable = false)
    private long mobile;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonBackReference
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "UserAddress{" +
                "addressId=" + addressId +
                ", pincode=" + pincode +
                ", area='" + area + '\'' +
                ", flat='" + flat + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", addressType='" + addressType + '\'' +
                ", defaultAddress=" + defaultAddress +
                ", name='" + name + '\'' +
                ", mobile=" + mobile +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}

