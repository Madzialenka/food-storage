package com.madzialenka.foodstorage.exception;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(Long id) {
        super(String.format("Food with id: %d not found", id));
    }
}
