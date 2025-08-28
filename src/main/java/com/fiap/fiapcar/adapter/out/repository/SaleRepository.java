package com.fiap.fiapcar.adapter.out.repository;

import com.fiap.fiapcar.adapter.out.repository.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    @Query("SELECT s FROM SaleEntity s WHERE s.id = :saleId")
    Optional<SaleEntity> findBySaleId(@Param("saleId") Long id);

    boolean existsByCarId(Long carId);
}
