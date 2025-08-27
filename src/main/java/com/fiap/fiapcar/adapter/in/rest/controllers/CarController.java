package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.mappers.CarMapper;
import com.fiap.fiapcar.application.ports.in.CarPort;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car")
@Tag(name = "Car Controller")
public class CarController {

    private final CarPort carPort;
    private final CarMapper carMapper;


    @PostMapping
    public ResponseEntity<String> create(@RequestBody CarRequest carRequest) {
        carPort.createNewCar(carMapper.toDTOFromRequest(carRequest));
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public Page<CarResponse> getByCryteria(
            @RequestParam(required = false) String status,
            @RequestParam(required = false, name = "brandId") String brandId,
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
        log.info("[CarController.getAll] Response: {}", resp.toString());
        return resp;
    }

    @GetMapping("/{carId}")
    public CarResponse get(
            @PathVariable Long carId
    ){
        log.info("[CarController.get] Request");
        CarResponse resp = carMapper.toResponseFromDTO(carPort.getCarById(carId));
        log.info("[CarController.get] Response: {}", resp.toString());
        return resp;
    }

    @PatchMapping("{carId}")
    public ResponseEntity<String> update(
            @PathVariable Long carId
    ){
        return ResponseEntity.ok("update");
    }

    @DeleteMapping("{carId}")
    public ResponseEntity<String> delete(
            @PathVariable Long carId
    ){
        return ResponseEntity.ok("delete");
    }
}
