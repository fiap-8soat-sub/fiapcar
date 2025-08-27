package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.CarMapper;
import com.fiap.fiapcar.adapter.out.repository.CarRepository;
import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CarDatabasePortImpl implements CarDatabasePort {

    private final CarMapper carMapper;
    private final CarRepository carRepository;


    @Override
    public List<CarDTO> getAllCars() {
        log.info("[CarDatabasePortImpl.getAllCars] Request");
        List<CarDTO> dtos = carMapper.toDTOFromEntityList(carRepository.findAll());
        log.info("[CarDatabasePortImpl.getAllCars] Response: {}", dtos.toString());
        return dtos;
    }

    @Override
    public CarDTO getCarById(Long id) {
        log.info("[CarDatabasePortImpl.getCarById] Request");
        Optional<CarEntity> carEntity = carRepository.findByCarId(id);
        if (carEntity.isPresent()) {
            CarDTO dto = carMapper.toDTOFromEntity(carEntity.get());
            log.info("[CarDatabasePortImpl.getCarById] Response: {}", dto.toString());
            return dto;
        }
        throw new RuntimeException("Car not found");
    }
}
