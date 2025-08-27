package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;


import io.swagger.v3.oas.annotations.media.Schema;

public record CarRequest(
        @Schema(
                name = "brandId",
                description = "",
                example = "VW"
        ) String brandId,
        @Schema(
                name = "model",
                description = "",
                example = "Tiguan Allspace R-Line 4Motion 350 TSI") String model,
        @Schema(
                name = "year",
                description = "",
                example = "2021"
        ) Integer year,
        @Schema(
                name = "color",
                description = "",
                example = "BLACK"
        ) String color,
        @Schema(
                name = "condition",
                description = "",
                example = "USED"
        ) String condition,
        @Schema(
                name = "fuelType",
                description = "",
                example = "GAS"
        ) String fuelType,
        @Schema(
                name = "transmission",
                description = "",
                example = "1"
        ) Long transmission,
        @Schema(
                name = "mileage",
                description = "",
                example = "52865"
        ) Integer mileage,
        @Schema(
                name = "price",
                description = "",
                example = "179.900"
        ) double price,
        @Schema(
                name = "plate",
                description = "",
                example = "GAP2B56"
        ) String plate,
        @Schema(
                name = "description",
                description = "",
                example = "PRETTY GOOD"
        ) String description
) {}

