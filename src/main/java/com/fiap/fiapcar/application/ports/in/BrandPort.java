package com.fiap.fiapcar.application.ports.in;

import com.fiap.fiapcar.application.model.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BrandPort {

    Page<BrandDTO> getAllBrands(Pageable pageable);

    BrandDTO getBrandById(Long id);

    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(BrandDTO brandDTO, Long id);
}
