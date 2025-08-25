package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;


import io.swagger.v3.oas.annotations.media.Schema;

public record CarRequest(
        @Schema(name = "", description = "", example = "") Long id,
        @Schema(name = "", description = "", example = "") String brandId,
        @Schema(
                name = "modelo",
                description = "O modelo do carro que eu quero comprar",
                example = "CIVIC"
        ) String model,
        @Schema(
                name = "ano",
                description = "Ano de lançamento",
                example = "2005"
        ) Integer year,
        @Schema(name = "", description = "", example = "") String color,
        @Schema(name = "", description = "", example = "") String condition,
        @Schema(name = "", description = "", example = "") String fuelType,
        @Schema(name = "", description = "", example = "") Long transmission,
        @Schema(
                name = "kmRodado",
                description = "O tanto que o carro rodou",
                example = "340000"
        ) Integer mileage,
        @Schema(name = "", description = "", example = "") double price,
        @Schema(name = "", description = "", example = "") String status,
        @Schema(name = "", description = "", example = "") String plate,
        @Schema(name = "", description = "", example = "") String description
) {}
