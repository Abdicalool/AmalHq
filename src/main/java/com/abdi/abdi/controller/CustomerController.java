package com.abdi.abdi.controller;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.entity.FrejaUser;
import com.abdi.abdi.event.LoginCompleEvent;
import com.abdi.abdi.freja.Freja;
import com.abdi.abdi.freja.FrejaResponse;
import com.abdi.abdi.listner.LoginCompleteListner;
import com.abdi.abdi.service.CustomerService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    LoginCompleteListner listner;
    @Autowired
    CustomerService customerService;
    @Autowired
    FrejaUser user;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/customer")

    public ResponseEntity<Customer> customer(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerBy(@PathVariable Long id) {
        Customer customer = customerService.getAllCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/freja")
    public ResponseEntity<String> frejaPlus(@RequestBody String user,BindingResult result) throws FrejaEidClientInternalException, FrejaEidException, InterruptedException, FrejaEidClientPollingException {
       //System.out.println(user);
        String freja = new Freja().result(user);
        //TimeUnit.SECONDS.sleep(5);
        String frejaResponse = new FrejaResponse().response(freja);
        publisher.publishEvent(new LoginCompleEvent(frejaResponse));
        if(frejaResponse!="APPROVED"){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{

            String token = JWT.create()
                    .withSubject("freja")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
                    .sign(Algorithm.HMAC512("cRfUjXn2r5u8x/A?D(G+KaPdSgVkYp3s6v9y$B&E)H@McQeThWmZq4t7w!z%C*F-"));

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + token);
            return new ResponseEntity<>("Bearer " + token,HttpStatus.OK);
        }

    }

    @GetMapping("/freja/{inputData}")
    public ResponseEntity<String> getREsult(@PathVariable String inputData) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        String frejaResponse = new FrejaResponse().response(inputData);
        return new ResponseEntity<>(frejaResponse.toString(), HttpStatus.OK);
    }
}
