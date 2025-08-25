package com.fiap.fiapcar.adapter.in.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sale")
@Tag(name = "Sale Controller")
public class SaleController {
    @PostMapping("/new")
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("get");
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<String> get(
            @PathVariable Long saleId
    ){
        return ResponseEntity.ok("get");
    }

    @PatchMapping("{saleId}")
    public ResponseEntity<String> update(
            @PathVariable Long saleId
    ){
        return ResponseEntity.ok("update");
    }
}
