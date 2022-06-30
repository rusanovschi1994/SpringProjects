package com.rusanovschi.spring.springboot.controllers;


import com.rusanovschi.spring.springboot.entity.Person;
import com.rusanovschi.spring.springboot.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/people")
public class PeopleController {

    private final PersonServiceImpl personService;

    @Autowired
    public PeopleController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<Person> getPeople(){

        return personService.getPeople();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){

        personService.savePerson(person);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable("id") int id){

        personService.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePerson(@PathVariable("id") int id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email){
        personService.updatePerson(id, name, email);
    }
}
