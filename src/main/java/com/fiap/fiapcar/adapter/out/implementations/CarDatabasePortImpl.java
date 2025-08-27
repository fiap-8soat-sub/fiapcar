package com.fiap.fiapcar.adapter.out.implementations;

import com.fiap.fiapcar.adapter.mappers.CarMapper;
import com.fiap.fiapcar.adapter.out.repository.CarRepository;
import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import com.fiap.fiapcar.adapter.out.repository.spec.CarSpecifications;
import com.fiap.fiapcar.application.model.CarDTO;
import com.fiap.fiapcar.application.ports.out.CarDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;


@Component
@Slf4j
@RequiredArgsConstructor
public class CarDatabasePortImpl implements CarDatabasePort {

    private final CarMapper carMapper;
    private final CarRepository carRepository;


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
        log.info("[CarDatabasePortImpl.getAllCars] Request");
        Specification<CarEntity> spec = Specification.allOf(
                CarSpecifications.hasStatus(status),
                CarSpecifications.hasBrandId(brandId),
                CarSpecifications.hasModelYear(modelYear),
                CarSpecifications.modelLike(model),
                CarSpecifications.priceGte(minPrice),
                CarSpecifications.priceLte(maxPrice)
        );
        Page<CarEntity> page = carRepository.findAll(spec, pageable);
        return page.map(carMapper::toDTOFromEntity);
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

    @Override
    public void createNewCar(CarDTO carDTO) {
        carRepository.save(carMapper.toEntityFromDTO(carDTO));
    }

    @Override
    public void updateCarById(CarDTO carDTO, Long id) {

    }
}
