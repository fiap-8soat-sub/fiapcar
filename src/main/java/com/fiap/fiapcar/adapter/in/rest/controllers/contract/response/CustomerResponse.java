package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.util.UUID;

public record CustomerResponse(
        String name,
        String email,
        UUID id,
        String password,
        String username
) {
}
