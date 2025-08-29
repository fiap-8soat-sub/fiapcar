package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;

public record CustomerRequest(
        String name,
        String email,
        String username
) { }
