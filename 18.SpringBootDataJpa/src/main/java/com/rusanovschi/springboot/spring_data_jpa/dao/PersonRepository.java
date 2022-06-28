package com.rusanovschi.springboot.spring_data_jpa.dao;


import com.rusanovschi.springboot.spring_data_jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    public List<Person> findAllByEmail(String email);

}
