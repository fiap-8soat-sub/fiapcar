package com.fiap.fiapcar.adapter.in.rest.handler;

public record ErrorResponse(
        String message,
        int code,
        String tracing
) { }
