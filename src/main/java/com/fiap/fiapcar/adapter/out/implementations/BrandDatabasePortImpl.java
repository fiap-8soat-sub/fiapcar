package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.BrandMapper;
import com.fiap.fiapcar.adapter.out.repository.BrandRepository;
import com.fiap.fiapcar.adapter.out.repository.entity.BrandEntity;
import com.fiap.fiapcar.application.model.BrandDTO;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class BrandDatabasePortImpl implements BrandDatabasePort {

    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;


    @Override
    public List<BrandDTO> getAllBrands() {
        log.info("[BrandDatabasePortImpl.getAllBrands] Request");
        List<BrandDTO> dtos = brandMapper.toDTOFromEntityList(brandRepository.findAll());
        log.info("[BrandDatabasePortImpl.getAllBrands] Response: {}", dtos.toString());
        return dtos;
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        log.info("[BrandDatabasePortImpl.getBrandById] Request");
        Optional<BrandEntity> brandEntity = brandRepository.findById(id);
        if (brandEntity.isPresent()) {
            BrandDTO dto = brandMapper.toDTOFromEntity(brandEntity.get());
            log.info("[BrandDatabasePortImpl.getBrandById] Response: {}", dto.toString());
            return dto;
        }
        throw new RuntimeException("Brand not found");
    }
}
