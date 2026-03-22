package com.ecommerce.amazio.repository;

import com.ecommerce.amazio.model.User;
import com.ecommerce.amazio.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserAddressRepo extends JpaRepository<UserAddress, Integer> {

    List<UserAddress> getUserAddressesByUser(User user);
}
