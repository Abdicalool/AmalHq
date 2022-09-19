package com.abdi.abdi.freja;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;

import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
public class FrejaResponse {
    String clientKeystorePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\Amal_Express.pfx";
    String clientKeystorePass = "5iTCTp";
    String serverCertificatePath = "C:\\Abdi\\abdi\\src\\main\\resources\\cert\\frejaca.pem";
    int maxWaitingTimeInSec = 120;
    public  String response(String inputData) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        SslSettings sslSettings;
        sslSettings = SslSettings.create(clientKeystorePath, clientKeystorePass, serverCertificatePath);
        AuthenticationClientApi authenticationClient = AuthenticationClient
                .create(sslSettings, FrejaEnvironment.TEST)
                .build();
        String authRef = inputData;
        AuthenticationResultRequest authenticationResultRequest =
                AuthenticationResultRequest
                        .create(authRef);
        AuthenticationResult response1 = authenticationClient
                .pollForResult(authenticationResultRequest, maxWaitingTimeInSec);
        String receivedAuthRef = response1.getStatus().toString();
        Base64.Decoder decoder = Base64.getMimeDecoder();

        byte[] bytes = decoder.decode(receivedAuthRef);
        String data = new String(bytes);
       // String[] resultArray = data.replaceAll("^[^{]*|[^}]*$", "").split("(?<=\\})[^{]*",3);
      // System.out.println(Arrays.toString(resultArray));

        //System.out.println(new String(bytes));
       // AuthenticationResult response = authenticationClient
         //       .getResult(authenticationResultRequest);
        //String receivedAuthRef = response.getDetails();
        //byte[] decodedBytes = Base64.getDecoder().decode(receivedAuthRef);
        //String decodedString = new String(decodedBytes);
        String token = JWT.create()
                .withSubject("freja")
                .withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
                .sign(Algorithm.HMAC512("cRfUjXn2r5u8x/A?D(G+KaPdSgVkYp3s6v9y$B&E)H@McQeThWmZq4t7w!z%C*F-"));
        HttpHeaders headers = new HttpHeaders();

        //headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return receivedAuthRef;
    }

}
