package com.abdi.abdi.listner;

import com.abdi.abdi.event.LoginCompleEvent;
import com.abdi.abdi.freja.Freja;
import com.abdi.abdi.freja.FrejaResponse;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginCompleteListner implements ApplicationListener<LoginCompleEvent> {
    FrejaResponse frejaResponse;
    String token;
    String clientKeystorePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx";
    String clientKeystorePass = "5iTCTp";
    String serverCertificatePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\frejaca.pem";

    @Override
    public void onApplicationEvent(LoginCompleEvent event) {
        System.out.println(event.getToken());


    }




}

