package com.fiap.fiapcar.adapter.beans;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
@EnableConfigurationProperties(CognitoProperties.class)
public class CognitoConfiguration {
    @Bean
    CognitoIdentityProviderClient cognito(CognitoProperties p) {
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(p.region()))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}