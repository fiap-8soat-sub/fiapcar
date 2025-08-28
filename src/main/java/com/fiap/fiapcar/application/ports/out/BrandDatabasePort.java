package com.fiap.fiapcar.application.ports.out;

import com.fiap.fiapcar.application.model.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BrandDatabasePort {

    Page<BrandDTO> getAllBrands(Pageable pageable);

    BrandDTO getBrandById(Long id);

    BrandDTO createBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(BrandDTO brandDTO, Long id);
}
