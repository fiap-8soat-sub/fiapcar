package com.fiap.fiapcar.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAR")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq_gen")
    @SequenceGenerator(name = "car_seq_gen", sequenceName = "car_id_seq", allocationSize = 1)
    @Column(name = "CAR_ID")
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private String brandId;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @Column(name = "CONDITION", nullable = false)
    private String condition;
    //ENUM HERE

    @Column(name = "FUEL_TYPE", nullable = false)
    private String fuelType;
    //ENUM HERE

    @Column(name = "TRANSMISSION", nullable = false)
    private Long transmission;

    @Column(name = "MILEAGE", nullable = false)
    private Integer mileage;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "STATUS", nullable = false)
    private String status;
    //ENUM HERE

    @Column(name = "PLATE", nullable = false)
    private String plate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDate updatedAt;
}
