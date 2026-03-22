package com.ecommerce.amazio.controller;

import com.ecommerce.amazio.exceptions.AddressNotFoundException;
import com.ecommerce.amazio.exceptions.InvalidCredentialsException;
import com.ecommerce.amazio.exceptions.UserAlreadyExistsException;
import com.ecommerce.amazio.exceptions.UserNotFoundException;
import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.model.UserAddress;
import com.ecommerce.amazio.requestDto.*;
import com.ecommerce.amazio.security.jwt.JwtService;
import com.ecommerce.amazio.security.service.AuthenticationService;
import com.ecommerce.amazio.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String,Object>> registerUser(@RequestBody User registerUser){
        Map<String,Object> response=new HashMap<>();
        try{
            User savedUser=userService.registerUser(registerUser);
            response.put("Success",true);
            response.put("message","Signed up successfully!!");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (UserAlreadyExistsException e){
            response.put("Success",false);
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String ,Object>> loginUser(@RequestBody LoginRequestDto loginRequest){
        Map<String,Object> response=new HashMap<>();

        try{
            User user=authenticationService.authenticateUser(loginRequest);
            String jwtToken=jwtService.generateJwtToken(user);
            UserResponseDto userResponse=new UserResponseDto(user.getUserId(), user.getEmail(), user.getFName(), user.getLName(), user.getMobile());

            response.put("Success",true);
            response.put("token",jwtToken);
            response.put("user",userResponse);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (UserNotFoundException | InvalidCredentialsException e){
            response.put("Success",false);
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/adminlogin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequestDto loginRequest){
        User user=authenticationService.authenticateAdmin(loginRequest);
        String jwtToken=jwtService.generateJwtToken(user);
        UserResponseDto userResponse=new UserResponseDto(user.getUserId(), user.getEmail(), user.getFName(), user.getLName(), user.getMobile());
        return ResponseEntity.ok(new TokenResponse(jwtToken,userResponse));
    }


    @PostMapping("/saveaddress")
    public ResponseEntity<?> saveAddress(@RequestBody UserAddressDto userAddress){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        String response=userService.saveAddress(userAddress,email);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/getaddress")
    public  ResponseEntity<?> getAddress(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        Map<String,Object> map=new HashMap<>();
        try {
            List<UserAddress> addresses=userService.getAddress(email);
            map.put("addresses",addresses);
            map.put("status",true);
        }catch (AddressNotFoundException e){
            map.put("status",false);
        }
//        Map<String,Object> response=Map.of("addresses",addresses,"status",true);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/updateaddress/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable int id, @RequestBody AddressRequestDto addressRequest){
        String response=userService.updateAddress(id,addressRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteaddress/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable int id){
        String response=userService.deleteAddress(id);
        return ResponseEntity.ok(response);
    }

}
