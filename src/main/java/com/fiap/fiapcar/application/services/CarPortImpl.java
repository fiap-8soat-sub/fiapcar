package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.ports.in.CarPort;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CarPortImpl implements CarPort {

    private final CarDatabasePort carDatabasePort;

    @Override
    public List<CarDTO> getAllCars() {
        log.info("[CarPortImpl.getAllCars] Request");
        List<CarDTO> dtos = carDatabasePort.getAllCars();
        log.info("[CarPortImpl.getAllCars] Response: {}", dtos.toString());
        return dtos;
    }

    @Override
    public CarDTO getCarById(Long id) {
        log.info("[CarPortImpl.getCarById] Request");
        CarDTO dto = carDatabasePort.getCarById(id);
        log.info("[CarPortImpl.getCarById] Response: {}", dto.toString());
        return dto;
    }
}
