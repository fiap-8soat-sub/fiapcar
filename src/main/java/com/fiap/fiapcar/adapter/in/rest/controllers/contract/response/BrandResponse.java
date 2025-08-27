package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.time.LocalDateTime;

public record BrandResponse(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
