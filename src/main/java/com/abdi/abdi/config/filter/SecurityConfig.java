package com.abdi.abdi.config.filter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Autowired
    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
       http
               .headers().frameOptions().disable()
               .and()
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/h2/**").permitAll()
               .antMatchers("/freja/**").permitAll()
               .antMatchers(HttpMethod.POST,"/create").permitAll()
               .anyRequest().authenticated()
               .and()
               .addFilter(authenticationFilter)
               .addFilterAfter(new JWTAuthorizationFilter(),AuthenticationFilter.class)
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       return http.build();

    }

}
