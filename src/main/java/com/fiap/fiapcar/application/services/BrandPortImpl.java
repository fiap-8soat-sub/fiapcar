package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.BrandDTO;
import com.fiap.fiapcar.application.ports.in.BrandPort;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class BrandPortImpl implements BrandPort {

    private final BrandDatabasePort  brandDatabasePort;

    @Override
    public List<BrandDTO> getAllBrands() {
        log.info("[BrandPortImpl.getAllBrands] Request");
        List<BrandDTO> dtos = brandDatabasePort.getAllBrands();
        log.info("[BrandPortImpl.getAllBrands] Response: {}", dtos.toString());
        return dtos;
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        log.info("[BrandPortImpl.getBrandById] Request");
        BrandDTO dto = brandDatabasePort.getBrandById(id);
        log.info("[BrandPortImpl.getBrandById] Response: {}", dto.toString());
        return dto;
    }
}
