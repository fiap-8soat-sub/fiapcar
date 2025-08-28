package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.SaleRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.SaleResponse;
import com.fiap.fiapcar.adapter.mappers.SaleMapper;
import com.fiap.fiapcar.application.ports.in.SalePort;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sale")
@Tag(name = "Sale Controller")
public class SaleController {

    private final SaleMapper saleMapper;
    private final SalePort salePort;

    @PostMapping("/new")
    public SaleResponse create(
            @Valid @RequestBody SaleRequest saleRequest
    ){
        log.info("[SaleController.create]");
        SaleResponse resp = saleMapper.toResponseFromNewDTO(
                salePort.createSale(
                        saleMapper.toDTOFromRequest(saleRequest)));
        log.info("[SaleController.create] Response: {}", resp);
        return resp;
    }

    @GetMapping("/all")
    public Page<SaleResponse> getAll(
            @ParameterObject @Parameter(description = "page,size,sort=field,asc|desc")
            Pageable pageable
    ){
        log.info("[SaleController.getAll]");
        Page<SaleResponse> resp = salePort.getAllSales(pageable
        ).map(saleMapper::toResponseFromDTO);
        log.info("[SaleController.getAll] Response: {}", resp);
        return resp;
    }

    @GetMapping("/{saleId}")
    public SaleResponse get(
            @PathVariable Long saleId
    ){
        log.info("[SaleController.get]");
        SaleResponse resp = saleMapper.toResponseFromNewDTO(salePort.getSaleById(saleId));
        log.info("[SaleController.get] Response: {}", resp.toString());
        return resp;
    }

    @PatchMapping("/reserved/{saleId}/pay")
    public SaleResponse update(
            @PathVariable Long saleId,
            @RequestBody SaleRequest saleRequest
    ){
        log.info("[SaleController.update]");
        SaleResponse resp = saleMapper.toResponseFromDTO(
                salePort.finalizeSale(saleMapper.toDTOFromRequest(saleRequest), saleId));
        log.info("[SaleController.update] Response: {}", resp.toString());
        return resp;
    }
}
