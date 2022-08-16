package com.rusanovschi.shop.beautifulnails.restcontrollers;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerService;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerErrorResponse;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerNotCreatedException;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<HttpStatus> addCustomer(@RequestBody @Valid Customer customer,
                                                  BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors){

                errorMsg
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CustomerNotCreatedException(errorMsg.toString());
        }

        customerService.saveCustomer(customer);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotFoundException exception){

        CustomerErrorResponse response = new CustomerErrorResponse(
                "Customer with this id doesn't exists",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotCreatedException exception){

        CustomerErrorResponse response = new CustomerErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
