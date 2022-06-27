package com.rusanovschi.spring.springboot.dao;


import com.rusanovschi.spring.springboot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Rusanovschi Cristian
 */
@Repository
public class PersonDAOImpl implements PersonDAO{


    private final EntityManager entityManager;

    @Autowired
    public PersonDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Person> getAllPeople() {

//        Session session = entityManager.unwrap(Session.class);
//        List<Person> allPerson = session.createQuery("from Person ").getResultList();
        Query query = entityManager.createQuery("from Person");
        List<Person> allPerson = query.getResultList();

        return allPerson;
    }

    @Override
    public void savePerson(Person person) {

//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(person);
        Person newPerson =  entityManager.merge(person);
        person.setId(newPerson.getId());
    }

    @Override
    public Person getPerson(int id) {

//        Session session = entityManager.unwrap(Session.class);
//        Person person = session.get(Person.class, id);
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {

//        Session session = entityManager.unwrap(Session.class);
//        Person personTobeUpdated = session.get(Person.class, id);
        Person personToBeUpdated = entityManager.find(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setPhone(updatedPerson.getPhone());
    }

    @Override
    public void deletePerson(int id) {

//        Session session = entityManager.unwrap(Session.class);
//        session.remove(session.get(Person.class, id));
        entityManager.remove(entityManager.find(Person.class, id));
    }
}
