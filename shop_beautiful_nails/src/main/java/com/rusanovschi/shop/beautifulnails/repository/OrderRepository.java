package com.rusanovschi.shop.beautifulnails.repository;

import com.rusanovschi.shop.beautifulnails.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
