package com.rusanovschi.springcourse.service;

import com.rusanovschi.springcourse.dao.PersonDAO;
import com.rusanovschi.springcourse.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }
}
