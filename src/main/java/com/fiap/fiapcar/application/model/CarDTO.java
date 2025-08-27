package com.fiap.fiapcar.application.model;


import java.time.LocalDate;

public class CarDTO {
    private Long id;
    private String brandId;
    private String model;
    private Integer year;
    private String color;
    private String condition;
    private String fuelType;
    private Long transmission;
    private Integer mileage;
    private double price;
    private String status;
    private String plate;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CarDTO(Long id, String brandId, String model, Integer year, String color, String condition, String fuelType, Long transmission, Integer mileage, double price, String status, String plate, String description, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.brandId = brandId;
        this.model = model;
        this.year = year;
        this.color = color;
        this.condition = condition;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
        this.price = price;
        this.status = status;
        this.plate = plate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CarDTO() {}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getTransmission() {
        return transmission;
    }

    public void setTransmission(Long transmission) {
        this.transmission = transmission;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
