package com.fiap.fiapcar.adapter.in.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private ErrorResponse runTimeException(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR WHILE HANDLING",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        exception.getMessage()
                );
        log.error("[GlobalExceptionHandler.runTimeException] {}", errorResponse);
        return errorResponse;
    }
}
