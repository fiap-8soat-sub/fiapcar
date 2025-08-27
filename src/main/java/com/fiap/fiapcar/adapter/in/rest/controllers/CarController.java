package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.CarResponse;
import com.fiap.fiapcar.adapter.mappers.CarMapper;
import com.fiap.fiapcar.application.ports.in.CarPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public List<CarResponse> getAll(){
        log.info("[CarController.getAll] Request");
        List<CarResponse> resp = carMapper.toResponseFromDTOList(carPort.getAllCars());
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
