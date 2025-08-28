package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record SaleResponse(
        Long id,
        UUID customerId,
        CarResponse car,
        String status,
        double amountPaid,
        LocalDateTime createdAt
) {
}
