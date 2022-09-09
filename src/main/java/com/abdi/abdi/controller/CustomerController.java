package com.abdi.abdi.controller;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.repo.CustomerRepo;
import com.abdi.abdi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<Customer> customer (@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomer(){
       List<Customer> customer = customerService.getAllCustomer();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerBy(@PathVariable Long id){
        Customer customer = customerService.getAllCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
