package com.rusanovschi.spring.springboot.service;

import com.rusanovschi.spring.springboot.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> getAllPeople();
    public void savePerson(Person person);
    public Person getPerson(int id);
    public void updatePerson(int id, Person updatedPerson);
    public void deletePerson(int id);
}
