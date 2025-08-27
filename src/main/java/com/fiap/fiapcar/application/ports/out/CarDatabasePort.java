package com.fiap.fiapcar.application.ports.out;

import com.fiap.fiapcar.application.model.CarDTO;

import java.util.List;

public interface CarDatabasePort {

    List<CarDTO> getAllCars();
    CarDTO getCarById(Long id);
}
