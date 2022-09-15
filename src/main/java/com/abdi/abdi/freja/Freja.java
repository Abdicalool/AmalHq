package com.abdi.abdi.freja;

import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;

public class Freja {

    String clientKeystorePath  = "C:\\keystore.ks";

    /*
     * The password of client keystore. Change the password (123123 in the
     * example) to match your setup.
     */

    String clientKeystorePass = "123123";

    /*
     * The server certificate. Change the path (C:\\certificate.cer in the
     * example) to match your setup.
     */

    String serverCertificatePath = "C:\\certificate.cer";

    /*
     * Initialize SslSettings with keystore parameters.
     */

    SslSettings sslSettings;

    {
        try {
            sslSettings = SslSettings
                    .create(clientKeystorePath, clientKeystorePass, serverCertificatePath);
        } catch (FrejaEidClientInternalException e) {
            System.out.println("Wrong user credential");
        }
    }
}
