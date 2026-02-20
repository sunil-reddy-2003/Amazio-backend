package com.ecommerce.amazio.repository;

import com.ecommerce.amazio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User,UUID> {

    @Query(nativeQuery = true,value = "SELECT * FROM APP_USER WHERE EMAIL=:email")
    public User getUserByEmail(String email);

    User getByEmail(String email);
}
