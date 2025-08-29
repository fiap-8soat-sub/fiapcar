package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.util.Map;
public record AuthResponse(
        boolean ok,
        String challenge,
        String session,
        Map<String,String> challengeParams,
        String idToken,
        String accessToken,
        String refreshToken,
        Integer expiresIn
) { }