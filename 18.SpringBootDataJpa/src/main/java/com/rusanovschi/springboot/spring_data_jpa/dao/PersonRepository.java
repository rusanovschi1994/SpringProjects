package com.rusanovschi.springboot.spring_data_jpa.dao;


import com.rusanovschi.springboot.spring_data_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
