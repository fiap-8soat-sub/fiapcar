package com.fiap.fiapcar.adapter.in.rest.controllers;

import com.fiap.fiapcar.adapter.in.rest.controllers.contract.response.BrandResponse;
import com.fiap.fiapcar.adapter.mappers.BrandMapper;
import com.fiap.fiapcar.application.ports.in.BrandPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/brand")
@Tag(name = "Brand Controller")
public class BrandController {

    private final BrandPort brandPort;
    private final BrandMapper brandMapper;


    @PostMapping
    public ResponseEntity<String> create(){
        return ResponseEntity.ok("create");
    }

    @GetMapping("/all")
    public List<BrandResponse> getAll(){
        log.info("[BrandController.getAll] Request");
        List<BrandResponse> resp = brandMapper.toResponseFromDTOList(brandPort.getAllBrands());
        log.info("[BrandController.getAll] Response: {}", resp.toString());
        return resp;
    }

    @GetMapping("/{brandId}")
    public BrandResponse get(
            @PathVariable Long brandId
    ){
        log.info("[BrandController.get] Request");
        BrandResponse resp = brandMapper.toResponseFromDTO(brandPort.getBrandById(brandId));
        log.info("[BrandController.get] Response: {}", resp.toString());
        return resp;
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
