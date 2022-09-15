package com.abdi.abdi.config.filter;

import com.abdi.abdi.entity.User;
import com.abdi.abdi.service.UserService;
import com.abdi.abdi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {

    @Autowired
    UserServiceImpl userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser(authentication.getName());
        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("Wrong password");
        }
       return  new UsernamePasswordAuthenticationToken(authentication.getName(),user.getPassword());
    }
}
