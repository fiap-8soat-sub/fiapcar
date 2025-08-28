package com.fiap.fiapcar.adapter.out.repository;

import com.fiap.fiapcar.adapter.out.repository.entity.CarEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {

    @Query("SELECT c FROM CarEntity c WHERE c.id = :carId")
    Optional<CarEntity> findByCarId(@Param("carId") Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update CarEntity c set c.status = :status where c.id = :carId")
    int updateStatus(@Param("carId") Long id, @Param("status") String status);

}
