package com.rusanovschi.springproject.dao;

import com.rusanovschi.springproject.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Rusanovschi Cristian
 */
@Repository
public class PersonDAOImpl implements PersonDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> getAllPeople() {

        Session session = sessionFactory.getCurrentSession();
        List<Person> allPerson = session.createQuery("from Person ").getResultList();

        return allPerson;
    }

    @Override
    public void savePerson(Person person) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public Person getPerson(int id) {

        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {

        Session session = sessionFactory.getCurrentSession();
        Person personTobeUpdated = session.get(Person.class, id);

        personTobeUpdated.setName(updatedPerson.getName());
        personTobeUpdated.setAge(updatedPerson.getAge());
        personTobeUpdated.setEmail(updatedPerson.getEmail());
        personTobeUpdated.setPhone(updatedPerson.getPhone());
    }

    @Override
    public void deletePerson(int id) {

        Session session = sessionFactory.getCurrentSession();
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
