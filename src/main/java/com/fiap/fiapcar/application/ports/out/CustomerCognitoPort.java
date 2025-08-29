package com.fiap.fiapcar.application.ports.out;

public interface CustomerCognitoPort {

    String register(String email, String pass, String username);
}
