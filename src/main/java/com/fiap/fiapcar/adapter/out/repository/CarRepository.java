package com.fiap.fiapcar.adapter.out.repository;

import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAll();
    @Query("SELECT c FROM CarEntity c WHERE c.id = :carId")
    Optional<CarEntity> findByCarId(@Param("carId") Long id);
}
