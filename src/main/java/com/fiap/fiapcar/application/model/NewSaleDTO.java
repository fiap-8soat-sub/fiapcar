package com.fiap.fiapcar.application.model;


import java.time.LocalDateTime;
import java.util.UUID;

public class NewSaleDTO {
    private Long id;
    private UUID customerId;
    private CarDTO car;
    private String status;
    private double amountPaid;
    private LocalDateTime createdAt;

    public NewSaleDTO(Long id, UUID customerId, CarDTO car, String status, double amountPaid, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.car = car;
        this.status = status;
        this.amountPaid = amountPaid;
        this.createdAt = createdAt;
    }
    public NewSaleDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
