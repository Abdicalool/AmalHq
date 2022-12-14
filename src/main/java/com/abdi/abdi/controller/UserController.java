package com.abdi.abdi.controller;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.entity.User;
import com.abdi.abdi.service.CustomerService;
import com.abdi.abdi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> user (@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User us = userService.createCustomer(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
