package com.rusanovschi.shop.beautifulnails.util;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerValidator implements Validator {

    private final CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Customer customer = (Customer) target;

        if(customerService.getCustomer(customer.getEmail()) != null){

            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
