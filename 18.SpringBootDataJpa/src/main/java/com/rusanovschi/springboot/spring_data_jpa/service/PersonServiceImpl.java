package com.rusanovschi.springboot.spring_data_jpa.service;

import com.rusanovschi.springboot.spring_data_jpa.dao.PersonRepository;
import com.rusanovschi.springboot.spring_data_jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPeople() {

        return personRepository.findAll();
    }

    @Override
    public void savePerson(Person person) {

        personRepository.save(person);
    }

    @Override
    public Person getPerson(int id) {

        Person person = null;
        Optional<Person> optional = personRepository.findById(id);
        if(optional.isPresent()){
            person = optional.get();
        }
        return person;
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {

        personRepository.save(updatedPerson);
    }

    @Override
    public void deletePerson(int id) {

        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAllByEmail(String email) {

        List<Person> findAllByEmail = personRepository.findAllByEmail(email);
        return findAllByEmail;
    }
}
