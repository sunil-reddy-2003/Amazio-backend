package com.ecommerce.amazio.security.config;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepo userRepository,
                                PasswordEncoder passwordEncoder) {
        return args -> {

            String adminEmail = "admin@amazio.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {

                User admin = new User();
                admin.setFName("Admin");
                admin.setLName("Amazio");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setMobile(9849345218L);
                admin.setUserRole(UserRole.ADMIN);
                admin.setCreatedAt(LocalDateTime.now());
                admin.setUpdatedAt(LocalDateTime.now());

                userRepository.save(admin);

                System.out.println("🔥 Admin user created successfully.");
            }
        };
    }
}