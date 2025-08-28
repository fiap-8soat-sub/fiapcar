package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.time.LocalDateTime;

public record CarResponse(
         Long id,
         Long brandId,
         String model,
         Integer year,
         String color,
         String condition,
         String fuelType,
         Long transmission,
         Integer mileage,
         double price,
         String status,
         String plate,
         String description,
         LocalDateTime createdAt,
         LocalDateTime updatedAt
) { }
