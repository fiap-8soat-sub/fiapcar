package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

public record CustomerResponse(
        String name,
        String email,
        String id,
        String password,
        String username
) {
}
