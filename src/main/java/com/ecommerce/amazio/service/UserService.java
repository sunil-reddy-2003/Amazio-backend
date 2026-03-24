package com.ecommerce.amazio.service;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.exceptions.AddressNotFoundException;
import com.ecommerce.amazio.exceptions.UserAlreadyExistsException;
import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.model.UserAddress;
import com.ecommerce.amazio.repository.UserAddressRepo;
import com.ecommerce.amazio.repository.UserRepo;
import com.ecommerce.amazio.requestDto.AddressRequestDto;
import com.ecommerce.amazio.requestDto.UserAddressDto;
import com.ecommerce.amazio.requestDto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    UserAddressRepo userAddressRepo;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder,UserAddressRepo userAddressRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
        this.userAddressRepo=userAddressRepo;
    }

    public User registerUser(User registerUser) {
        User user=new User();
        user.setFName(registerUser.getFName());
        user.setLName(registerUser.getLName());
        if(userRepo.getByEmail(registerUser.getEmail())!=null){
            throw new UserAlreadyExistsException("User Already Exists!!");
        }
        user.setEmail(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode( registerUser.getPassword()));
        user.setMobile(registerUser.getMobile());
        user.setUserRole(UserRole.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepo.save(user);
    }

    public String saveAddress(UserAddressDto userAddress, String email) {
        UserAddress address=new UserAddress();
        address.setPincode(userAddress.getPincode());
        address.setArea(userAddress.getArea());
        address.setFlat(userAddress.getFlat());
        address.setLandmark(userAddress.getLandmark());
        address.setCity(userAddress.getCity());
        address.setState(userAddress.getState());
        address.setAddressType(userAddress.getAddressType());
//        address.setDefaultAddress(userAddress.isDefaultAddress());
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());
        address.setName(userAddress.getName());
        address.setMobile(userAddress.getMobile());

        User user=userRepo.getByEmail(email);
        List<UserAddress> addresses=user.getUserAddress();
        log.info("inside save address with addresses: "+addresses.toString());
        if (addresses.isEmpty()) {
            addresses=new ArrayList<>();
            address.setDefaultAddress(true);
            log.info("inside IF: "+address);
        }else if(userAddress.isDefaultAddress()) {
            for (UserAddress add:addresses){
                add.setDefaultAddress(false);
            }
            address.setDefaultAddress(userAddress.isDefaultAddress());
            log.info("inside ELSE-IF: "+address.toString());
        }
        else {
            address.setDefaultAddress(false);
            log.info("inside ELSE: "+address.toString());
        }
        address.setUser(user);
        addresses.add(address);
        log.info("user: "+user);

        userAddressRepo.save(address);
        return "Address saved successfully";
    }

    public List<UserAddress> getAddress(String email) {
        User user=userRepo.getByEmail(email);
        List<UserAddress> addresses= userAddressRepo.getUserAddressesByUser(user);
        if(addresses.isEmpty()){
            throw new AddressNotFoundException("No saved addresses");
        }
        return addresses;
    }


    public String updateAddress(int id, AddressRequestDto addressRequest,String email) {
        User user=userRepo.getByEmail(email);
        List<UserAddress> addresses=user.getUserAddress();
        if(addressRequest.isDefaultAddress()){
            for (UserAddress add:addresses){
                add.setDefaultAddress(false);
            }
        }

        UserAddress address=userAddressRepo.getReferenceById(id);
        address.setPincode(addressRequest.getPincode());
        address.setArea(addressRequest.getArea());
        address.setFlat(addressRequest.getFlat());
        address.setLandmark(addressRequest.getLandmark());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setAddressType(addressRequest.getAddressType());
        address.setDefaultAddress(addressRequest.isDefaultAddress());
        address.setName(addressRequest.getName());
        address.setMobile(addressRequest.getMobile());

        userAddressRepo.save(address);

        return "Address updated successfully";
    }

    public String deleteAddress(int id) {
        userAddressRepo.deleteById(id);
        return "address deleted successfully";
    }

    public User updateUser(UUID id, UserRequestDto userDetails) {
        User user=userRepo.getReferenceById(id);
        user.setFName(userDetails.getFName());
        user.setLName(userDetails.getLName());
        user.setEmail(userDetails.getEmail());
        user.setMobile(userDetails.getMobile());
        return userRepo.save(user);
    }
}
