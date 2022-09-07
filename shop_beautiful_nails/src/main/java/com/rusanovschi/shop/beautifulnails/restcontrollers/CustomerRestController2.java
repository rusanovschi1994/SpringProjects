package com.rusanovschi.shop.beautifulnails.restcontrollers;

import com.rusanovschi.shop.beautifulnails.dto.CustomerDTO;
import com.rusanovschi.shop.beautifulnails.entity.Customer;
import com.rusanovschi.shop.beautifulnails.service.CustomerService;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerErrorResponse;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerNotCreatedException;
import com.rusanovschi.shop.beautifulnails.util.restError.CustomerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerRestController2 {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerRestController2(CustomerService customerService,
                                   ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers(){

        return customerService.getCustomers().stream().map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") Integer id){

        return convertToCustomerDTO(customerService.getCustomer(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addCustomer(@RequestBody @Valid CustomerDTO customerDTO,
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

        customerService.saveCustomer(convertToCustomer(customerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> removeCustomer(@PathVariable("id") Integer id){

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> updateCustomer(@PathVariable("id") Integer id,
                                                     @RequestParam(required = false) String firstName,
                                                     @RequestParam(required = false) String email){

        customerService.updateCustomer(id, firstName, email);

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

    public Customer convertToCustomer(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO convertToCustomerDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
}
