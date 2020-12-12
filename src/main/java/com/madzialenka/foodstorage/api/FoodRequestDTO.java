package com.madzialenka.foodstorage.api;

import com.madzialenka.foodstorage.common.MeasurementUnit;

import java.time.LocalDate;
import java.util.Objects;

public class FoodRequestDTO {
    private String name;
    private MeasurementUnit measurementUnit;
    private Long amount;
    private LocalDate bestBefore;

    public FoodRequestDTO() {
    }

    public FoodRequestDTO(String name, MeasurementUnit measurementUnit, Long amount, LocalDate bestBefore) {
        this.name = name;
        this.measurementUnit = measurementUnit;
        this.amount = amount;
        this.bestBefore = bestBefore;
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
        FoodRequestDTO that = (FoodRequestDTO) o;
        return Objects.equals(name, that.name) &&
                measurementUnit == that.measurementUnit &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(bestBefore, that.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, measurementUnit, amount, bestBefore);
    }
}
