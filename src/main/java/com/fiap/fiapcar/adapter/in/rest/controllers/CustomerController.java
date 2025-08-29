package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CustomerRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.SaleRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CustomerResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.SaleResponse;
import com.fiap.fiapcar.adapter.mappers.CustomerMapper;
import com.fiap.fiapcar.application.ports.in.CustomerPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
@Tag(name = "Customer Controller")
public class CustomerController {

    private final CustomerMapper customerMapper;
    private final CustomerPort customerPort;

    @Operation(
            summary = "Create new Customer",
            description = "This endpoint will create a new customer")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public CustomerResponse create(
            @Valid @RequestBody CustomerRequest customerRequest
    ){
        log.info("[CustomerController.create]");
        CustomerResponse resp = customerMapper.toResponseFromDTO(
                customerPort.create(
                        customerMapper.toDTOFromRequest(customerRequest)));
        log.info("[CustomerController.create] Response: {}", resp);
        return resp;
    }

}
