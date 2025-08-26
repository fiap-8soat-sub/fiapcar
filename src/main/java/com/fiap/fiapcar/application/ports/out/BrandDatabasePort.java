package com.fiap.fiapcar.application.ports.out;

import com.fiap.fiapcar.application.model.BrandDTO;

import java.util.List;

public interface BrandDatabasePort {

    List<BrandDTO> getAllBrands();

    BrandDTO getBrandById(Long id);
}
