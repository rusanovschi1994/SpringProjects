package com.rusanovschi.springproject.service;



import com.rusanovschi.springproject.entity.Person;

import java.util.List;

public interface PersonService {

    public List<Person> getAllPeople();
    public void savePerson(Person person);
    public Person getPerson(int id);
    public void updatePerson(int id, Person updatedPerson);
}
