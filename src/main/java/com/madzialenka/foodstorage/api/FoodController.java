package com.madzialenka.foodstorage.api;

import com.madzialenka.foodstorage.service.FoodService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("food")
@RestController
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<FoodResponseDTO> getAllFood(@RequestParam(required = false) String sortBy,
                                            @RequestParam(required = false) Sort.Direction direction,
                                            @RequestParam(required = false) String name) {
        return foodService.getAllFood(sortBy, direction, name);
    }
}
