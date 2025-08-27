package com.fiap.fiapcar.application.ports.in;

import com.fiap.fiapcar.application.model.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface CarPort {

    Page<CarDTO> getCarsByCryteria(
            String status,
            String brandId,
            Integer modelYear,
            String model,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable);

    CarDTO getCarById(Long id);

    void createNewCar(CarDTO carDTO);
    void updateCarById(CarDTO carDTO, Long id);
}
