package com.rusanovschi.spring.springboot.service;

import com.rusanovschi.spring.springboot.entity.Person;
import com.rusanovschi.spring.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    public Person getPerson(int id){

        Person person = null;
        Optional<Person> optional = personRepository.findById(id);

        if(optional.isPresent()){
            person = optional.get();
        }else{
            throw new IllegalStateException("person with id="+ id + " does not exist");
        }

        return person;
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

    @Transactional
    public void updatePerson(int id, String name, String email){

        Person person= personRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("student with id=" + id +
                                                            "does not exist"));
        if(name != null &&
            name.length() > 0 &&
            !Objects.equals(person.getName(), name)) {

            person.setName(name);
        }

        if(email != null &&
            email.length() > 0 &&
            !Objects.equals(person.getEmail(), email)){

            Optional<Person> optionalPerson = personRepository.findPersonByEmail(email);
            if(optionalPerson.isPresent()){
                throw new IllegalStateException("email is taken");
            }
            person.setEmail(email);
        }
    }
}
