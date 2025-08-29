package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.application.ports.out.CustomerCognitoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType;

@Service
@RequiredArgsConstructor
public class CustomerCognitoPortImpl implements CustomerCognitoPort {
    private final CognitoIdentityProviderClient cognito;

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    @Override
    public String register(String email, String pass, String username) {
        try {
            var create = cognito.adminCreateUser(AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .userAttributes(
                            AttributeType.builder().name("email").value(email).build(),
                            AttributeType.builder().name("email_verified").value("true").build()
                    )
                    .messageAction(MessageActionType.SUPPRESS)
                    .build());

            cognito.adminSetUserPassword(AdminSetUserPasswordRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .password(pass)
                    .permanent(true)
                    .build());

            var sub = create.user().attributes().stream()
                    .filter(a -> "sub".equals(a.name()))
                    .map(AttributeType::value)
                    .findFirst()
                    .orElse(null);

            if (sub == null) {
                var user = cognito.adminGetUser(b -> b.userPoolId(userPoolId).username(email));
                sub = user.userAttributes().stream()
                        .filter(a -> "sub".equals(a.name()))
                        .map(AttributeType::value)
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("sub not found on Cognito"));
            }
            return sub;

        } catch (software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException e) {
            throw new IllegalArgumentException("Username already exist on Cognito", e);
        } catch (software.amazon.awssdk.services.cognitoidentityprovider.model.InvalidPasswordException e) {
            throw new IllegalArgumentException("password don't match with Cognito security policy", e);
        }
    }
}
