package com.fiap.fiapcar.adapter.in.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand Controller")
public class BrandController {
    @PostMapping
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("get");
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<String> get(
            @PathVariable Long brandId
    ){
        return ResponseEntity.ok("get");
    }

    @PatchMapping("{brandId}")
    public ResponseEntity<String> update(
            @PathVariable Long brandId
    ){
        return ResponseEntity.ok("update");
    }

    @DeleteMapping("{brandId}")
    public ResponseEntity<String> delete(
            @PathVariable Long brandId
    ){
        return ResponseEntity.ok("delete");
    }
}
