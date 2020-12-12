package com.madzialenka.foodstorage.service;

import com.madzialenka.foodstorage.api.FoodResponseDTO;
import com.madzialenka.foodstorage.model.Food;
import com.madzialenka.foodstorage.repository.FoodRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    public static final String DEFAULT_SORT_BY = "id";
    public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<FoodResponseDTO> getAllFood(String sortBy, Sort.Direction direction, String name) {
        sortBy = Optional.ofNullable(sortBy).orElse(DEFAULT_SORT_BY);
        direction = Optional.ofNullable(direction).orElse(DEFAULT_SORT_DIRECTION);
        List<Food> allFood;
        if(name != null) {
            allFood = foodRepository.findByNameStartingWith(name, Sort.by(direction, sortBy));
        } else {
            allFood = foodRepository.findAll(Sort.by(direction, sortBy));
        }
        return allFood.stream()
                .map(this::mapToFoodResponseDTO)
                .collect(Collectors.toList());
    }

    private FoodResponseDTO mapToFoodResponseDTO(Food food) {
        FoodResponseDTO foodResponseDTO = new FoodResponseDTO();
        foodResponseDTO.setId(food.getId());
        foodResponseDTO.setName(food.getName());
        foodResponseDTO.setMeasurementUnit(food.getMeasurementUnit());
        foodResponseDTO.setAmount(food.getAmount());
        foodResponseDTO.setBestBefore(food.getBestBefore());
        return foodResponseDTO;
    }
}
