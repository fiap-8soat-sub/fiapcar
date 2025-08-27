package com.fiap.fiapcar.adapter.in.rest.controllers.contract.response;

import java.time.LocalDate;

public record CarResponse(
         Long id,
         String brandId,
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
         LocalDate createdAt,
         LocalDate updatedAt
) { }
