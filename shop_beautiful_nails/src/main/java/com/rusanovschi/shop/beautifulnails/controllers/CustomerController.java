package com.rusanovschi.shop.beautifulnails.controllers;


import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.security.OurUserDetails;
import com.rusanovschi.shop.beautifulnails.service.CustomerService;
import com.rusanovschi.shop.beautifulnails.util.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerValidator customerValidator;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OurUserDetails ourUserDetails = (OurUserDetails) authentication.getPrincipal();
        System.out.println(ourUserDetails.getUser());
        return "customer/newCustomer";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer,
                               BindingResult bindingResult){

        customerValidator.validate(customer, bindingResult);

        if(bindingResult.hasErrors()){

            return "customer/newCustomer";
        }

        customerService.saveCustomer(customer);
        return "customer/registrationResult";
    }

    @GetMapping(path = "/{id}/edit")
    public String editCustomer(Model model,
                               @PathVariable("id") Integer id){

        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/editCustomer";
    }

    @PatchMapping(path = "/{id}")
    public String updateCustomer(@ModelAttribute("customer") @Valid Customer customer,
                                 @PathVariable("id") Integer id,
                                 BindingResult bindingResult) {

        customerValidator.validate(customer, bindingResult);

        if(bindingResult.hasErrors()){
            return "customer/editCustomer";
        }

        customerService.updateCustomer(id, customer.getFirstName(), customer.getEmail());

        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id){

        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
