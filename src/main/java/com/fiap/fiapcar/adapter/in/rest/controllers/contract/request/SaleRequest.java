package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;


import java.util.UUID;

public record SaleRequest(
        UUID customerId,
        Long carId,
        double amountPaid
) { }
