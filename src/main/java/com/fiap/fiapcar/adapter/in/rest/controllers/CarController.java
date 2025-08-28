package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.mappers.CarMapper;
import com.fiap.fiapcar.application.ports.in.CarPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car")
@Tag(name = "Car Controller")
public class CarController {

    private final CarPort carPort;
    private final CarMapper carMapper;


    @Operation(
            summary = "Create new Car",
            description = "This endpoint will create a new car with parameters")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CarResponse create(@RequestBody CarRequest carRequest) {
        log.info("[CarController.create]");
        return carMapper.toResponseFromDTO(
                carPort.createNewCar(
                        carMapper.toDTOFromRequest(carRequest)));
    }

    @Operation(
            summary = "Get list of cars using cryteria",
            description = "This endpoint returns a list of cars with cryterias")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public Page<CarResponse> getByCryteria(
            @RequestParam(required = false) String status,
            @RequestParam(required = false, name = "brandId") Long brandId,
            @RequestParam(required = false, name = "modelYear") Integer modelYear,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @ParameterObject @Parameter(description = "page,size,sort=field,asc|desc")
            Pageable pageable
    ){
        log.info("[CarController.getAll] Request");
        Page<CarResponse> resp = carPort.getCarsByCryteria(
                status, brandId, modelYear, model, minPrice, maxPrice, pageable
        ).map(carMapper::toResponseFromDTO);
        log.info("[CarController.getAll] Response: {}", resp);
        return resp;
    }


    @Operation(
            summary = "Get specific car by id",
            description = "This endpoint returns a single car by his id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{carId}")
    public CarResponse getCarById(
            @PathVariable Long carId
    ){
        log.info("[CarController.get] Request");
        CarResponse resp = carMapper.toResponseFromDTO(carPort.getCarById(carId));
        log.info("[CarController.get] Response: {}", resp.toString());
        return resp;
    }

    @Operation(
            summary = "Update a specific car by id",
            description = "This endpoint update a single car by his id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{carId}")
    public CarResponse updateCarByid(
            @PathVariable Long carId,
            @RequestBody CarRequest carRequest
    ){
        CarResponse resp = carMapper.toResponseFromDTO(
                carPort.updateCarById(carMapper.toDTOFromRequest(carRequest), carId));
        log.info("[CarController.update] Response: {}", resp.toString());
        return resp;
    }
}
