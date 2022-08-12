package com.rusanovschi.shop.beautifulnails.restcontrollers;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerService;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerErrorResponse;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerRestController2 {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController2(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){

        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id){

        return customerService.getCustomer(id);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handler(CustomerNotFoundException exception){

        CustomerErrorResponse response = new CustomerErrorResponse(
                "Customer with this id doesn't exists",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
