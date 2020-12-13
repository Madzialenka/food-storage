package com.madzialenka.foodstorage.service;

import com.madzialenka.foodstorage.api.FoodRequestDTO;
import com.madzialenka.foodstorage.api.FoodResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FoodService {

    List<FoodResponseDTO> getAllFood(String sortBy, Sort.Direction direction, String name);

    FoodResponseDTO addFood(FoodRequestDTO foodRequestDTO);

    FoodResponseDTO updateFood(Long id, FoodRequestDTO foodRequestDTO);

    FoodResponseDTO deleteFood(Long id);
}
