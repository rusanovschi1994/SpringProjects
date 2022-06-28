package com.rusanovschi.springboot.spring_data_jpa.service;

import com.rusanovschi.springboot.spring_data_jpa.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> getAllPeople();
    public void savePerson(Person person);
    public Person getPerson(int id);
    public void updatePerson(int id, Person updatedPerson);
    public void deletePerson(int id);

    public List<Person> findAllByEmail(String email);
}
