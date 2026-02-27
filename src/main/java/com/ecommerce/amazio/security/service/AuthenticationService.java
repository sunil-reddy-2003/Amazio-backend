package com.ecommerce.amazio.security.service;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.exceptions.InvalidCredentialsException;
import com.ecommerce.amazio.exceptions.UserNotFoundException;
import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.repository.UserRepo;
import com.ecommerce.amazio.requestDto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public User authenticateUser(LoginRequestDto loginRequest) {
        String email=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        User customer=userRepo.getUserByEmail(email);
        if(!customer.getUserRole().equals(UserRole.CUSTOMER)) throw new UserNotFoundException("Unauthorized user");
        if(customer==null) throw new UserNotFoundException("no user found with the email: "+email);
        if(!passwordEncoder.matches(password, customer.getPassword())){
            throw new InvalidCredentialsException("password or email is incorrect");
        }
        return customer;
    }

    public User authenticateAdmin(LoginRequestDto loginRequest) {
        String email=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        User admin=userRepo.getUserByEmail(email);
        if(!admin.getUserRole().equals(UserRole.ADMIN)) throw new UserNotFoundException("Unauthorized user");
        if(admin==null) throw new UserNotFoundException("no user found with the email: "+email);
        if(!passwordEncoder.matches(password, admin.getPassword())){
            throw new InvalidCredentialsException("password or email is incorrect");
        }
        return admin;

    }
}
