package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.adapter.out.repository.entity.BrandEntity;
import com.fiap.fiapcar.application.model.BrandDTO;
import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.ports.in.BrandPort;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class BrandPortImpl implements BrandPort {

    private final BrandDatabasePort  brandDatabasePort;

    @Override
    public Page<BrandDTO> getAllBrands(Pageable pageable) {
        log.info("[BrandPortImpl.getAllBrands] Request");
        return brandDatabasePort.getAllBrands(pageable);
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        log.info("[BrandPortImpl.getBrandById] Request");
        return Optional.ofNullable(brandDatabasePort.getBrandById(id))
                .orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        log.info("[BrandPortImpl.createBrand]");
        return brandDatabasePort.createBrand(fillBrandDetails(brandDTO));
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO, Long id) {
        log.info("[BrandPortImpl.updateBrand]");
        BrandDTO brandDTOToUpdate = brandDatabasePort.getBrandById(id);
        Optional.ofNullable(brandDTOToUpdate)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return brandDatabasePort.updateBrand(updateBrandInfos(brandDTO, brandDTOToUpdate), id);
    }

    private BrandDTO fillBrandDetails(BrandDTO brandDTO) {
        log.info("[BrandPortImpl.fillBrandetails]");
        brandDTO.setCreatedAt(LocalDateTime.now());
        brandDTO.setUpdatedAt(LocalDateTime.now());
        return brandDTO;
    }

    private BrandDTO updateBrandInfos(BrandDTO newBrand, BrandDTO databaseBrand) {
        Optional.ofNullable(newBrand.getName()).ifPresent(databaseBrand::setName);
        databaseBrand.setUpdatedAt(LocalDateTime.now());
        return databaseBrand;
    }
}
