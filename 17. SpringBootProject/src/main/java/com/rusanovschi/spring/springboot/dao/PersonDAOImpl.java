package com.rusanovschi.spring.springboot.dao;


import com.rusanovschi.spring.springboot.entity.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

        Session session = entityManager.unwrap(Session.class);
        List<Person> allPerson = session.createQuery("from Person ").getResultList();

        return allPerson;
    }

    @Override
    public void savePerson(Person person) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(person);
    }

    @Override
    public Person getPerson(int id) {

        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        return person;
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {

        Session session = entityManager.unwrap(Session.class);
        Person personTobeUpdated = session.get(Person.class, id);

        personTobeUpdated.setName(updatedPerson.getName());
        personTobeUpdated.setAge(updatedPerson.getAge());
        personTobeUpdated.setEmail(updatedPerson.getEmail());
        personTobeUpdated.setPhone(updatedPerson.getPhone());
    }

    @Override
    public void deletePerson(int id) {

        Session session = entityManager.unwrap(Session.class);
        session.remove(session.get(Person.class, id));
    }

//    public Person show(int id) {
//
//       return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new PersonMapper())
//               .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//
//        jdbcTemplate.update("INSERT INTO Person VALUES (default , ?, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail(), person.getPhone());
//    }
//
//    public void update(int id, Person updatedPerson) {
//
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, phone=? WHERE id=?",updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getPhone(), id);
//    }
//
//    public void delete(int id) {
//
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }


}
