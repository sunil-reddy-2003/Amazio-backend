package com.ecommerce.amazio.service;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.exceptions.UserNotFoundException;
import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.model.UserAddress;
import com.ecommerce.amazio.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public User registerUser(User registerUser) {
        User user=new User();
        user.setFName(registerUser.getFName());
        user.setLName(registerUser.getLName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode( registerUser.getPassword()));
        user.setMobile(registerUser.getMobile());
        user.setUserRole(UserRole.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepo.save(user);
    }


//    public User getUserDetailsById(UUID id) {
//        if(!userRepo.existsById(id)){
//            throw new UserNotFoundException("No user found with id: "+id);
//        }
//        return userRepo.findById(id).get();
//    }
}
