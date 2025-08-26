package com.fiap.fiapcar.adapter.out.repository;

import com.fiap.fiapcar.adapter.out.repository.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    List<BrandEntity> findAll();

    @Query("SELECT b FROM BrandEntity b WHERE b.id = :brandId")
    Optional<BrandEntity> findByBrandId(@Param("brandId") Long brandId);



}
