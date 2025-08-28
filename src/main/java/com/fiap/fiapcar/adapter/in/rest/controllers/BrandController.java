package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.BrandRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.mappers.BrandMapper;
import com.fiap.fiapcar.application.ports.in.BrandPort;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand Controller")
public class BrandController {

    private final BrandPort brandPort;
    private final BrandMapper brandMapper;


    @Operation(
            summary = "Create a new brand",
            description = "This endpoint create a new brand")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public BrandResponse create(
            @RequestBody @Valid BrandRequest brandRequest
            ){
        log.info("[BrandPortImpl.createBrand]");
        BrandResponse resp = brandMapper.toResponseFromDTO(
                brandPort.createBrand(
                        brandMapper.toDTOFromRequest(brandRequest)));
        log.info("[BrandPortImpl.createBrand] Response: {}", resp);
        return resp;
    }

    @Operation(
            summary = "List all brands",
            description = "This endpoint return a list of brands")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public Page<BrandResponse> getAll(
            @ParameterObject @Parameter(description = "page,size,sort=field,asc|desc")
            Pageable pageable
    ){
        log.info("[BrandController.getAll] Request");
        Page<BrandResponse> resp = brandPort.getAllBrands(pageable).map(brandMapper::toResponseFromDTO);
        log.info("[BrandController.getAll] Response: {}", resp.toString());
        return resp;
    }

    @Operation(
            summary = "Get specific brand by id",
            description = "This endpoint returns a single brand by his id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{brandId}")
    public BrandResponse get(
            @PathVariable Long brandId
    ){
        log.info("[BrandController.get] Request");
        BrandResponse resp = brandMapper.toResponseFromDTO(brandPort.getBrandById(brandId));
        log.info("[BrandController.get] Response: {}", resp.toString());
        return resp;
    }

    @Operation(
            summary = "Update specific brand by id",
            description = "This endpoint update a single brand by his id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("{brandId}")
    public BrandResponse update(
            @PathVariable Long brandId,
            @RequestBody @Valid BrandRequest brandRequest
    ){
        log.info("[BrandController.update]");
        BrandResponse resp = brandMapper.toResponseFromDTO(
                brandPort.updateBrand(
                        brandMapper.toDTOFromRequest(brandRequest), brandId));
        log.info("[BrandController.update] Response: {}", resp.toString());
        return resp;
    }
}
