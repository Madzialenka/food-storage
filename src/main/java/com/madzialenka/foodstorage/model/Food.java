package com.madzialenka.foodstorage.model;

import com.madzialenka.foodstorage.common.MeasurementUnit;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "food_type", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_unit", nullable = false)
    private MeasurementUnit measurementUnit;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "best_before", nullable = false)
    private LocalDate bestBefore;

    public Food() {
    }

    public Food(Long id, String name, MeasurementUnit measurementUnit, Long amount, LocalDate bestBefore) {
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
        Food food = (Food) o;
        return Objects.equals(id, food.id) &&
                Objects.equals(name, food.name) &&
                measurementUnit == food.measurementUnit &&
                Objects.equals(amount, food.amount) &&
                Objects.equals(bestBefore, food.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, measurementUnit, amount, bestBefore);
    }
}
