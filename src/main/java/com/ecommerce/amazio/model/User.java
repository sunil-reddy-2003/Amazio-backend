package com.ecommerce.amazio.model;


import com.ecommerce.amazio.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,unique = true)
    private UUID userId;

    @Column(nullable = false)
    private String fName;

    @Column(nullable = false)
    private String lName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private long mobile;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserAddress> userAddress;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile=" + mobile +
                ", userRole=" + userRole +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
