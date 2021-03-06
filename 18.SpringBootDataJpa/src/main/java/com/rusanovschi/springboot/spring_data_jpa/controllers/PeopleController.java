package com.rusanovschi.springboot.spring_data_jpa.controllers;

import com.rusanovschi.springboot.spring_data_jpa.entity.Person;
import com.rusanovschi.springboot.spring_data_jpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Rusanovschi Cristian
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String showAll(Model model) {

        model.addAttribute("people", personService.getAllPeople());
        return "people/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "people/new";

        personService.savePerson(person);
        return "people/registrationResult";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {

        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){

        personService.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/email/{email}")
    public String showAllPersonByEmail(@PathVariable String email, Model model){

        //List<Person> people = personService.findAllByEmail(email);
        model.addAttribute("people", personService.findAllByEmail(email));
        return "people/showAll";
    }
}
