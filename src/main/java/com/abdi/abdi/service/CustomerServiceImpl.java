package com.abdi.abdi.service;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getAllCustomerById(Long id) {
       return  customerRepo.findById(id).get();
    }
}
