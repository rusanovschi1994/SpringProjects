package com.rusanovschi.springproject.service;


import com.rusanovschi.springproject.dao.PersonDAO;
import com.rusanovschi.springproject.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public List<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {

        personDAO.savePerson(person);
    }
}
