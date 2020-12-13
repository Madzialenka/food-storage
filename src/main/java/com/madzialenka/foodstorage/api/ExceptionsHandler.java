package com.madzialenka.foodstorage.api;

import com.madzialenka.foodstorage.exception.FoodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

//    2 methods, first one a little bit simpler

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler({FoodNotFoundException.class})
//    public ErrorDTO handleFoodNotFoundException(FoodNotFoundException e) {
//        return new ErrorDTO(e.getMessage());
//    }

    @ExceptionHandler({FoodNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleFoodNotFoundException(FoodNotFoundException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
