package com.fiap.fiapcar.adapter.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.cognito")
public record CognitoProperties(
        String region,
        String userPoolId,
        String appClientId,
        String appClientSecret
) {}

