package com.rusanovschi.spring.springboot.service;

import com.rusanovschi.spring.springboot.entity.Person;
import com.rusanovschi.spring.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    public void savePerson(Person person){

        Optional<Person> optionalPerson = personRepository.findPersonByEmail(person.getEmail());
        if(optionalPerson.isPresent())
            throw new IllegalStateException("email is taken");


        personRepository.save(person);
        System.out.println(person);
    }

    public void deletePerson(int id){
       boolean exist = personRepository.existsById(id);
       if(!exist){
           throw new IllegalStateException("Person with id = " + id + " ,does not exist");
       }
       personRepository.deleteById(id);
    }

    public void updatePerson(int id, String name, String email){

    }
}
