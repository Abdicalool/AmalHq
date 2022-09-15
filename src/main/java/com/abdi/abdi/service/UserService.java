package com.abdi.abdi.service;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createCustomer(User user);
}
