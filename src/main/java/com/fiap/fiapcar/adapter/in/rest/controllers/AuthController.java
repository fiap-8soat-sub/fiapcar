package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.AuthResponse;
import com.fiap.fiapcar.adapter.mappers.AuthMapper;
import com.fiap.fiapcar.application.ports.in.CognitoAuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CognitoAuthPort svc;
    private final AuthMapper authMapper;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestParam String username,
            @RequestParam String password) {
        return authMapper.toResponseFromDTO(svc.login(username, password));
    }

    @PostMapping("/new-password")
    public AuthResponse newPassword(
            @RequestParam String username,
            @RequestParam String newPassword,
            @RequestParam String session) {
        return authMapper.toResponseFromDTO(svc.respondNewPassword(username, newPassword, session));
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(
            @RequestParam String username,
            @RequestParam String refreshToken) {
        return authMapper.toResponseFromDTO(svc.refresh(username, refreshToken));
    }
}
