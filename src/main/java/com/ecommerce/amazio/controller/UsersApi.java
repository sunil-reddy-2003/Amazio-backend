package com.ecommerce.amazio.controller;

import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.requestDto.LoginRequestDto;
import com.ecommerce.amazio.requestDto.TokenResponse;
import com.ecommerce.amazio.requestDto.UserResponseDto;
import com.ecommerce.amazio.security.jwt.JwtService;
import com.ecommerce.amazio.security.service.AuthenticationService;
import com.ecommerce.amazio.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UsersApi {

    UserService userService;
    AuthenticationService authenticationService;
    JwtService jwtService;

    @Autowired
    public UsersApi(UserService userService,AuthenticationService authenticationService,JwtService jwtService) {
        this.userService = userService;
        this.authenticationService=authenticationService;
        this.jwtService=jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User registerUser){
        User savedUser=userService.registerUser(registerUser);
        return ResponseEntity.ok(savedUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequest){
        User user=authenticationService.authenticateUser(loginRequest);
        String jwtToken=jwtService.generateJwtToken(user);
        UserResponseDto userResponse=new UserResponseDto(user.getUserId(), user.getEmail(), user.getFName(), user.getLName(), user.getMobile());
        return ResponseEntity.ok(new TokenResponse(jwtToken,userResponse));
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserDetailsById(@PathVariable UUID id){
//        User user= userService.getUserDetailsById(id);
//        UserResponseDto userResponseDto=new UserResponseDto(user.getUserId(),);
//    }

}
