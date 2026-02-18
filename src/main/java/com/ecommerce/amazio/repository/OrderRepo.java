package com.ecommerce.amazio.repository;

import com.ecommerce.amazio.model.Address;
import com.ecommerce.amazio.model.Order;
import com.ecommerce.amazio.model.OrderItem;
import com.ecommerce.amazio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {

     List<Order> findByUser(User user);
}