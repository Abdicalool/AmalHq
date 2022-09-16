package com.abdi.abdi.freja;

import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.beans.general.SsnUserInfo;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.*;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Data
public class Freja {

    String clientKeystorePath  = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx";
    String clientKeystorePass = "5iTCTp";
    String serverCertificatePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\frejaca.pem";
    SslSettings sslSettings;


   public String result(String personnummer){
       try {
           sslSettings = SslSettings.create(clientKeystorePath, clientKeystorePass, serverCertificatePath);
           AuthenticationClientApi authenticationClient = AuthenticationClient.create(sslSettings, FrejaEnvironment.TEST).build();
           SsnUserInfo ssn = SsnUserInfo.create(Country.SWEDEN, personnummer);
           AttributeToReturn[] attributes = {AttributeToReturn.BASIC_USER_INFO,

                   AttributeToReturn.AGE,

           };
           InitiateAuthenticationRequest initiateAuthenticationRequest = InitiateAuthenticationRequest
                   .createCustom().setSsn(ssn)
                   .setMinRegistrationLevel(MinRegistrationLevel.PLUS)
                   .setAttributesToReturn(attributes)
                   .build();
           try {
               String transactionReference = authenticationClient
                       .initiate(initiateAuthenticationRequest);
           //    System.out.println(transactionReference);
               return transactionReference;
           } catch (FrejaEidException e) {
               throw new RuntimeException(e);
           }

       } catch (FrejaEidClientInternalException e) {
           throw new RuntimeException(e);
       }

   }





}
