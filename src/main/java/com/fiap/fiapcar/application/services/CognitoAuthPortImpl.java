package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.adapter.beans.CognitoProperties;
import com.fiap.fiapcar.application.model.AuthDTO;
import com.fiap.fiapcar.application.ports.in.CognitoAuthPort;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CognitoAuthPortImpl implements CognitoAuthPort {
    private final CognitoIdentityProviderClient cip;
    private final CognitoProperties props;

    public CognitoAuthPortImpl(CognitoIdentityProviderClient cip, CognitoProperties props) {
        this.cip = cip; this.props = props;
    }

    @Override
    public AuthDTO login(String username, String password) {
        try {
            Map<String,String> params = new HashMap<>();
            params.put("USERNAME", username);
            params.put("PASSWORD", password);
            maybeSecretHash(params, username);

            InitiateAuthResponse r = cip.initiateAuth(b -> b
                    .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                    .clientId(props.appClientId())
                    .authParameters(params)
            );

            if (r.challengeName() != null) {
                // sem MFA, o que pode aparecer é NEW_PASSWORD_REQUIRED no 1º login
                return new AuthDTO(
                        "",
                        false,
                        r.challengeNameAsString(),
                        r.session(),
                        r.challengeParameters(),
                        null,
                        null,
                        null
                );
            }
            var t = r.authenticationResult();
            return new AuthDTO(
                    t.refreshToken(),
                    true,
                    null,
                    null,
                    null,
                    t.idToken(),
                    t.accessToken(),
                    t.expiresIn()
            );
        } catch (NotAuthorizedException e) {          // senha errada / refresh inválido
            throw new IllegalArgumentException("Usuário ou senha inválidos.", e);
        } catch (UserNotConfirmedException e) {       // usuário não confirmado
            throw new IllegalStateException("Usuário não confirmado.", e);
        } catch (PasswordResetRequiredException e) {  // exige reset de senha
            throw new IllegalStateException("Reset de senha obrigatório.", e);
        } catch (CognitoIdentityProviderException e) { // fallback
            throw new IllegalStateException("Erro Cognito: " + e.awsErrorDetails().errorMessage(), e);
        }
    }

    @Override
    public AuthDTO respondNewPassword(String username, String newPassword, String session) {
        Map<String,String> cr = new HashMap<>();
        cr.put("USERNAME", username);
        cr.put("NEW_PASSWORD", newPassword);
        maybeSecretHash(cr, username);

        var r = cip.respondToAuthChallenge(b -> b
                .challengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .clientId(props.appClientId())
                .session(session)
                .challengeResponses(cr)
        );
        var t = r.authenticationResult();
        return new AuthDTO(
                t.refreshToken(),
                true,
                null,
                null,
                null,
                t.idToken(),
                t.accessToken(),
                t.expiresIn()
        );
    }

    @Override
    public AuthDTO refresh(String username, String refreshToken) {
        Map<String,String> params = new HashMap<>();
        params.put("REFRESH_TOKEN", refreshToken);
        maybeSecretHash(params, username);

        var r = cip.initiateAuth(b -> b
                .authFlow(AuthFlowType.REFRESH_TOKEN_AUTH)
                .clientId(props.appClientId())
                .authParameters(params)
        );
        var t = r.authenticationResult();
        return new AuthDTO(
                t.refreshToken(),
                true,
                null,
                null,
                null,
                t.idToken(),
                t.accessToken(),
                t.expiresIn()
        );
    }

    private void maybeSecretHash(Map<String,String> m, String username) {
        var secret = props.appClientSecret();
        if (secret != null && !secret.isBlank()) {
            m.put("SECRET_HASH", secretHash(username, props.appClientId(), secret));
        }
    }
    private static String secretHash(String username, String clientId, String clientSecret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return Base64.getEncoder().encodeToString(mac.doFinal((username + clientId).getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) { throw new IllegalStateException(e); }
    }
}