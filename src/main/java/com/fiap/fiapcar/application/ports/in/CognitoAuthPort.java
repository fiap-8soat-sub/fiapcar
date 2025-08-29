package com.fiap.fiapcar.application.ports.in;

import com.fiap.fiapcar.application.model.AuthDTO;
import com.fiap.fiapcar.application.services.CognitoAuthPortImpl;

public interface CognitoAuthPort {

    AuthDTO login(String username, String password);
    AuthDTO respondNewPassword (String username, String newPassword, String session);
    AuthDTO refresh (String username, String refreshToken);

}
