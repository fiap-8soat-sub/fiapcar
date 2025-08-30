package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.BrandMapper;
import com.fiap.fiapcar.adapter.out.repository.BrandRepository;
import com.fiap.fiapcar.application.model.BrandDTO;
import com.fiap.fiapcar.application.ports.out.BrandDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class BrandDatabasePortImpl implements BrandDatabasePort {

    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;


    @Override
    public Page<BrandDTO> getAllBrands(Pageable pageable) {
        log.info("[BrandDatabasePortImpl.getAllBrands] Request");
        return brandRepository.findAll(pageable).map(brandMapper::toDTOFromEntity);
    }

    @Override
    public BrandDTO getBrandById(Long id) {
        log.info("[BrandDatabasePortImpl.getBrandById] Request");
        return brandMapper.toDTOFromEntity(brandRepository.findById(id).orElse(null));
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        log.info("[BrandDatabasePortImpl.createBrand]");
        return brandMapper.toDTOFromEntity(
                brandRepository.save(
                        brandMapper.toEntityFromDTO(brandDTO)));
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO, Long id) {
        log.info("[BrandDatabasePort.updateBrand]");
        return brandMapper.toDTOFromEntity(
                brandRepository.save(brandMapper.toEntityFromDTO(brandDTO)));
    }
}
