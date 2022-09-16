package com.abdi.abdi.controller;

import com.abdi.abdi.entity.Customer;
import com.abdi.abdi.event.LoginCompleEvent;
import com.abdi.abdi.freja.Freja;
import com.abdi.abdi.freja.FrejaResponse;
import com.abdi.abdi.repo.CustomerRepo;
import com.abdi.abdi.service.CustomerService;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.beans.general.SsnUserInfo;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.Country;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.enums.RegistrationState;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

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

    @GetMapping("/freja")
    public ResponseEntity<String> frejaPlus() throws FrejaEidClientInternalException, FrejaEidException, InterruptedException, FrejaEidClientPollingException {
        String freja = new Freja().result("198101039413");
        TimeUnit.SECONDS.sleep(5);
        String frejaResponse = new FrejaResponse().response(freja);
        publisher.publishEvent(new LoginCompleEvent(frejaResponse));
        return new ResponseEntity<>(frejaResponse, HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/freja/{inputData}")
    public ResponseEntity<String> getREsult(@PathVariable String inputData) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        String frejaResponse = new FrejaResponse().response(inputData);
        return new ResponseEntity<>(frejaResponse.toString(), HttpStatus.OK);
    }
}
