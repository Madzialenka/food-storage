package com.madzialenka.foodstorage.api;

import com.madzialenka.foodstorage.common.MeasurementUnit;

import java.time.LocalDate;
import java.util.Objects;

public class FoodResponseDTO {
    private Long id;
    private String name;
    private MeasurementUnit measurementUnit;
    private Long amount;
    private LocalDate bestBefore;

    public FoodResponseDTO() {
    }

    public FoodResponseDTO(Long id, String name, MeasurementUnit measurementUnit, Long amount, LocalDate bestBefore) {
        this.id = id;
        this.name = name;
        this.measurementUnit = measurementUnit;
        this.amount = amount;
        this.bestBefore = bestBefore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodResponseDTO that = (FoodResponseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                measurementUnit == that.measurementUnit &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(bestBefore, that.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measurementUnit, amount, bestBefore);
    }
}
