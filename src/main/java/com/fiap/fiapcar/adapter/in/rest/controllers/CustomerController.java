package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CustomerRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.SaleRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CustomerResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.SaleResponse;
import com.fiap.fiapcar.adapter.mappers.CustomerMapper;
import com.fiap.fiapcar.application.ports.in.CustomerPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Operation(
            summary = "Get specific Customer by id",
            description = "This endpoint returns a single customer by his id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{customerId}")
    public CustomerResponse get(
            @PathVariable UUID customerId
    ){
        log.info("[CustomerController.get] Request");
        CustomerResponse resp = customerMapper.toResponseFromDTO(customerPort.getCustomerById(customerId));
        log.info("[CustomerController.get] Response: {}", resp.toString());
        return resp;
    }

    @Operation(
            summary = "List all Customers",
            description = "This endpoint return a list of Customers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public Page<CustomerResponse> getAll(
            @ParameterObject @Parameter(description = "page,size,sort=field,asc|desc")
            Pageable pageable
    ){
        log.info("[CustomerController.getAll] Request");
        Page<CustomerResponse> resp = customerPort.getAllCustomers(pageable).map(customerMapper::toResponseFromDTO);
        log.info("[CustomerController.getAll] Response: {}", resp.toString());
        return resp;
    }

}
