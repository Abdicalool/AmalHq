package com.abdi.abdi.listner;

import com.abdi.abdi.config.filter.CustomAuthenticationManager;
import com.abdi.abdi.entity.FrejaUser;
import com.abdi.abdi.event.LoginCompleEvent;
import com.abdi.abdi.freja.Freja;
import com.abdi.abdi.freja.FrejaResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoginCompleteListner implements ApplicationListener<LoginCompleEvent> {

   CustomAuthenticationManager authenticationManager;
    @Autowired
    FrejaUser user;


    @Override
    public void onApplicationEvent(LoginCompleEvent event) {
        System.out.println(event.getToken());


    }



}
