package com.abdi.abdi.service;

import com.abdi.abdi.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomer();

    Customer getAllCustomerById(Long id);
}
