package com.abdi.abdi.service;

import com.abdi.abdi.entity.User;
import com.abdi.abdi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User createCustomer(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    public User getUser(String username){
        User user = userRepo.findByUsername(username);
        return  user;
    }
}
