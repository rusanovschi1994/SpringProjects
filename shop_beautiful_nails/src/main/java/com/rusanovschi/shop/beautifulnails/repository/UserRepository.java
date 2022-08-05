package com.rusanovschi.shop.beautifulnails.repository;

import com.rusanovschi.shop.beautifulnails.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
