package com.madzialenka.foodstorage.api;

import com.madzialenka.foodstorage.service.FoodService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public FoodResponseDTO addFood(@RequestBody FoodRequestDTO foodRequestDTO) {
        return foodService.addFood(foodRequestDTO);
    }

    @PutMapping("{id}")
    public FoodResponseDTO updateFood(@PathVariable("id") Long id, @RequestBody FoodRequestDTO foodRequestDTO) {
        return foodService.updateFood(id, foodRequestDTO);
    }

    @DeleteMapping("{id}")
    public FoodResponseDTO deleteFood(@PathVariable("id") Long id) {
        return foodService.deleteFood(id);
    }
}
