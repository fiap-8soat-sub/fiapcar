package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.request.CarRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car")
@Tag(name = "Car Controller")
public class CarController {
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CarRequest carRequest) {
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("get");
    }

    @GetMapping("/{carId}")
    public ResponseEntity<String> get(
            @PathVariable Long carId
    ){
        return ResponseEntity.ok("get");
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
