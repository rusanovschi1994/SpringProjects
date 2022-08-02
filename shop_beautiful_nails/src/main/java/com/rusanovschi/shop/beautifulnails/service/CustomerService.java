package com.rusanovschi.shop.beautifulnails.service;

import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){

        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer id){

        Customer customer = null;
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isPresent()){

            customer = optional.get();
        }else{
            throw new IllegalStateException("Customer with id="+id+" doesn't exist");
        }

        return customer;
    }

    public Customer getCustomer(String email){

        Customer customer = null;
        Optional<Customer> optional = customerRepository.findCustomerByEmail(email);

        if(optional.isPresent())
            customer = optional.get();

        return customer;
    }


    public void saveCustomer(Customer customer){

        Optional<Customer> optional = customerRepository.findCustomerByEmail(customer.getEmail());
        if(optional.isPresent()){

            throw new IllegalArgumentException ("email is taken");
        }
        customer.setCreatedAt(new Date());
        customer.setEnabled(true);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){

        boolean exists= customerRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Customer with id="+id+" doesn't exist");
        }

        customerRepository.deleteById(id);
    }

    public void updateCustomer(Integer id, String firstName, String email){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer with id="+id+" doesn't exist"));

        if(firstName != null &&
            firstName.length() > 0 &&
             !Objects.equals(customer.getFirstName(), firstName)){

            customer.setFirstName(firstName);
        }

        if(email != null &&
            email.length() > 0 &&
             !Objects.equals(customer.getEmail(), email)){

//            Optional<Customer> optional = customerRepository.findCustomerByEmail(customer.getEmail());
//            if(optional.isPresent()){
//
//                throw new IllegalStateException("Customer with email="+email+" is registered");
//            }

            customer.setEmail(email);
        }
    }
}
