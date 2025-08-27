package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.ports.in.CarPort;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
public class CarPortImpl implements CarPort {

    private final CarDatabasePort carDatabasePort;

    @Override
    public Page<CarDTO> getCarsByCryteria(
            String status,
            String brandId,
            Integer modelYear,
            String model,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable
    ) {
        log.info("[CarPortImpl.getCarsByCryteria] Request");
        Page<CarDTO> dtos = carDatabasePort.getCarsByCryteria(status, brandId, modelYear, model, minPrice, maxPrice, pageable);
        log.info("[CarPortImpl.getCarsByCryteria] Response: {}", dtos.toString());
        return dtos;
    }

    @Override
    public CarDTO getCarById(Long id) {
        log.info("[CarPortImpl.getCarById] Request");
        CarDTO dto = carDatabasePort.getCarById(id);
        log.info("[CarPortImpl.getCarById] Response: {}", dto.toString());
        return dto;
    }

    @Override
    public void createNewCar(CarDTO carDTO) {
        carDatabasePort.createNewCar(fillCarDetails(carDTO));
    }

    @Override
    public void updateCarById(CarDTO carDTO, Long id) {

    }

    CarDTO fillCarDetails(CarDTO carDTO) {
        log.info("[CarPortImpl.fillCarDetails] Request");
        carDTO.setCreatedAt(LocalDate.now());
        carDTO.setUpdatedAt(LocalDate.now());
        carDTO.setStatus("AVALIABLE");
        return carDTO;
    }
}
