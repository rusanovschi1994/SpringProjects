package com.rusanovschi.shop.beautifulnails.repository;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
