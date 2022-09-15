package com.abdi.abdi.controller;

import com.abdi.abdi.entity.Customer;
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
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.Base64;
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
    @GetMapping("/freja")
    public  ResponseEntity<String> frejaPlus(){
        File file = new File("C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx");
        if (file.isFile()){
            String clientKeystorePath  = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx";

            /*
             * The password of client keystore. Change the password (123123 in the
             * example) to match your setup.
             */

            String clientKeystorePass = "5iTCTp";

            /*
             * The server certificate. Change the path (C:\\certificate.cer in the
             * example) to match your setup.
             */

            String serverCertificatePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\frejaca.pem";

            /*
             * Initialize SslSettings with keystore parameters.
             */

            SslSettings sslSettings;

            {
                try {
                    sslSettings = SslSettings
                            .create(clientKeystorePath, clientKeystorePass, serverCertificatePath);
                    AuthenticationClientApi authenticationClient = AuthenticationClient
                            .create(sslSettings, FrejaEnvironment.TEST)
                            .build();
                    RegistrationState minRegistrationLevel = RegistrationState.PLUS;
                    SsnUserInfo ssn = SsnUserInfo
                            .create(Country.SWEDEN, "198101039413");
                    InitiateAuthenticationRequest initiateAuthenticationRequest =
                            InitiateAuthenticationRequest
                                    .createCustom()
                                    .setSsn(ssn)
                                    .setMinRegistrationLevel(MinRegistrationLevel.PLUS)
                                    .build();
                    try {
                        String transactionReference = authenticationClient
                                .initiate(initiateAuthenticationRequest);
                        System.out.println(transactionReference);
                    } catch (FrejaEidException e) {
                        throw new RuntimeException(e);
                    }
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                } catch (FrejaEidClientInternalException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            System.out.println("Not Found");
        }
  return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);


    }
    @GetMapping("/freja/{inputData}")
    public ResponseEntity<String> getREsult(@PathVariable String inputData ) throws FrejaEidClientInternalException, FrejaEidException {
        String clientKeystorePath  = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx";

        /*
         * The password of client keystore. Change the password (123123 in the
         * example) to match your setup.
         */

        String clientKeystorePass = "5iTCTp";

        /*
         * The server certificate. Change the path (C:\\certificate.cer in the
         * example) to match your setup.
         */

        String serverCertificatePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\frejaca.pem";

        /*
         * Initialize SslSettings with keystore parameters.
         */

        SslSettings sslSettings;
        sslSettings = SslSettings
                .create(clientKeystorePath, clientKeystorePass, serverCertificatePath);
        AuthenticationClientApi authenticationClient = AuthenticationClient
                .create(sslSettings, FrejaEnvironment.TEST)
                .build();
        String authRef =inputData;
        AuthenticationResultRequest authenticationResultRequest =
                AuthenticationResultRequest
                        .create(authRef);
        AuthenticationResult response = authenticationClient
                .getResult(authenticationResultRequest);
        String receivedAuthRef = response.getDetails();
        //byte[] decodedBytes = Base64.getDecoder().decode(receivedAuthRef);
        //String decodedString = new String(decodedBytes);
        return new ResponseEntity<>(receivedAuthRef.toString(),HttpStatus.OK);
    }
}
