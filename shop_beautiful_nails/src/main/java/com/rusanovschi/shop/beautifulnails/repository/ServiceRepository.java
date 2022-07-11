package com.rusanovschi.shop.beautifulnails.repository;

import com.rusanovschi.shop.beautifulnails.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
