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
import java.time.LocalDateTime;

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
        log.info("[CarPortImpl.getCarsByCryteria]");
        return carDatabasePort.getCarsByCryteria(
                status, brandId, modelYear, model, minPrice, maxPrice, pageable);
    }

    @Override
    public CarDTO getCarById(Long id) {
        log.info("[CarPortImpl.getCarById]");
        return carDatabasePort.getCarById(id);
    }

    @Override
    public void createNewCar(CarDTO carDTO) {
        carDatabasePort.createNewCar(fillCarDetails(carDTO));
    }

    @Override
    public CarDTO updateCarById(CarDTO carDTO, Long id) {
        log.info("[CarPortImpl.updateCarById] Request");
        carDTO.setUpdatedAt(LocalDateTime.now());
        return carDatabasePort.updateCarById(carDTO, id);
    }

    CarDTO fillCarDetails(CarDTO carDTO) {
        log.info("[CarPortImpl.fillCarDetails]");
        carDTO.setCreatedAt(LocalDateTime.now());
        carDTO.setUpdatedAt(LocalDateTime.now());
        carDTO.setStatus("AVALIABLE");
        return carDTO;
    }
}
