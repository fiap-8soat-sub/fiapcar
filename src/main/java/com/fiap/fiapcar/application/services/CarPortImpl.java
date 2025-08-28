package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
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
import java.util.Optional;

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
        return Optional.ofNullable(carDatabasePort.getCarById(id))
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public CarDTO createNewCar(CarDTO carDTO) {
        return carDatabasePort.createNewCar(fillCarDetails(carDTO));
    }

    @Override
    public CarDTO updateCarById(CarDTO carDTO, Long id) {
        log.info("[CarPortImpl.updateCarById] Request");
        CarDTO carDTOToUpdate = carDatabasePort.getCarById(id);
        Optional.ofNullable(carDTOToUpdate)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return carDatabasePort.updateCarById(
                updateCarInfos(carDTO, carDTOToUpdate), id);
    }

    private CarDTO fillCarDetails(CarDTO carDTO) {
        log.info("[CarPortImpl.fillCarDetails]");
        carDTO.setCreatedAt(LocalDateTime.now());
        carDTO.setUpdatedAt(LocalDateTime.now());
        carDTO.setStatus("AVALIABLE");
        return carDTO;
    }

    private CarDTO updateCarInfos(CarDTO newCar, CarDTO databaseCar) {
        Optional.ofNullable(newCar.getDescription()).ifPresent(databaseCar::setDescription);
        Optional.ofNullable(newCar.getCondition()).ifPresent(databaseCar::setCondition);
        Optional.ofNullable(newCar.getBrandId()).ifPresent(databaseCar::setBrandId);
        Optional.ofNullable(newCar.getColor()).ifPresent(databaseCar::setColor);
        Optional.ofNullable(newCar.getMileage()).ifPresent(databaseCar::setMileage);
        Optional.ofNullable(newCar.getModel()).ifPresent(databaseCar::setModel);
        Optional.of(newCar.getPrice()).ifPresent(databaseCar::setPrice);
        Optional.ofNullable(newCar.getStatus()).ifPresent(databaseCar::setStatus);
        Optional.ofNullable(newCar.getYear()).ifPresent(databaseCar::setYear);
        Optional.ofNullable(newCar.getFuelType()).ifPresent(databaseCar::setFuelType);
        Optional.ofNullable(newCar.getTransmission()).ifPresent(databaseCar::setTransmission);
        Optional.ofNullable(newCar.getUpdatedAt()).ifPresent(databaseCar::setUpdatedAt);
        databaseCar.setUpdatedAt(LocalDateTime.now());
        return databaseCar;
    }
}
