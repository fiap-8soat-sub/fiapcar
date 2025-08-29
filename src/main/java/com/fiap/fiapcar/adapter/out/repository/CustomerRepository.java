package com.fiap.fiapcar.adapter.out.repository;

import com.fiap.fiapcar.adapter.out.repository.entity.CustomerEntity;
import com.fiap.fiapcar.adapter.out.repository.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.id = :customerId")
    Optional<SaleEntity> findByCustomerId(@Param("customerId") Long id);

}
