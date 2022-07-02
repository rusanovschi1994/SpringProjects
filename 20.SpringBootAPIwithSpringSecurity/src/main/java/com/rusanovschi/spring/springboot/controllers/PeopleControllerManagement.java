package com.rusanovschi.spring.springboot.controllers;

import com.rusanovschi.spring.springboot.entity.Person;
import com.rusanovschi.spring.springboot.service.PersonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/people")
public class PeopleControllerManagement {

    private final PersonServiceImpl personService;

    public PeopleControllerManagement(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople(){

        return personService.getPeople();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){

        personService.savePerson(person);
    }

    @PutMapping(path = "/{id}")
    public void updatePerson(@PathVariable("id") Integer id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email){

        personService.updatePerson(id, name, email);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable("id") Integer id){

        personService.deletePerson(id);
    }
}
