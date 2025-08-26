package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.time.LocalDate;

public record BrandResponse(
        Long id,
        String name,
        LocalDate createdAt,
        LocalDate updatedAt
) { }
