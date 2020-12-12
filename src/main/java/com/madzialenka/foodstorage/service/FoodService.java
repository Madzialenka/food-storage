package com.madzialenka.foodstorage.service;

import com.madzialenka.foodstorage.api.FoodResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FoodService {

    List<FoodResponseDTO> getAllFood(String sortBy, Sort.Direction direction, String name);
}
