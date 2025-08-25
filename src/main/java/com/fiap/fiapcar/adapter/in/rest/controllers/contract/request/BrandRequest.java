package com.fiap.fiapcar.adapter.in.rest.controllers.contract.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record BrandRequest(
        @Schema(name = "", description = "", example = "") String name
) {
}
