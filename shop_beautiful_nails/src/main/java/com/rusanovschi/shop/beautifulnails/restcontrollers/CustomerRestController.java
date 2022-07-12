package com.rusanovschi.shop.beautifulnails.restcontrollers;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController {

    public final CustomerServiceImpl customerService;

    @Autowired
    public CustomerRestController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {

        return customerService.getCustomers();
    }

    @GetMapping(path = "/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id){

        return customerService.getCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer){

        customerService.saveCustomer(customer);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){

        customerService.deleteCustomer(id);
    }

    @PutMapping(path = "/{id}")
    public void updateCustomer(@PathVariable("id") Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email){

        customerService.updateCustomer(id, name, email);
    }
}
