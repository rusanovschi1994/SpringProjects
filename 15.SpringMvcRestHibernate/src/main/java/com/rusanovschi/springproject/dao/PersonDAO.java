package com.rusanovschi.springproject.dao;

import com.rusanovschi.springproject.entity.Person;

import java.util.List;

public interface PersonDAO {

    public List<Person> getAllPeople();

    public void savePerson(Person person);

    public Person getPerson(int id);

    public void updatePerson(int id, Person person);

    public void deletePerson(int id);
}
