package com.rusanovschi.shop.beautifulnails.controllers;


import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public String getCustomers(Model model) {

        model.addAttribute("customers" , customerService.getCustomers());
        return "customer/getCustomers";
    }

    @GetMapping("/{id}")
    public String getCustomer(@PathVariable("id") Integer id, Model model){

        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/getCustomer";
    }

    @GetMapping("/new")
    public String newCustomer(Model model){

        model.addAttribute("customer", new Customer());
        return "customer/newCustomer";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "customer/newCustomer";
        }

        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }
}
