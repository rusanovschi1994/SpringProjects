package com.rusanovschi.spring.springboot.controllers;

import com.rusanovschi.spring.springboot.entity.Person;
import com.rusanovschi.spring.springboot.service.PersonServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Person> getPeople(){

        return personService.getPeople();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('person:write')")
    public void addPerson(@RequestBody Person person){

        personService.savePerson(person);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public void updatePerson(@PathVariable("id") Integer id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email){

        personService.updatePerson(id, name, email);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('person:write')")
    public void deletePerson(@PathVariable("id") Integer id){

        personService.deletePerson(id);
    }
}
