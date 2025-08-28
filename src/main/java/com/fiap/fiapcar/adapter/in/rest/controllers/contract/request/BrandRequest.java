package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BrandRequest(
        @Schema(name = "name", description = "Brand of cars", example = "BYD") String name
) {
}
