package com.madzialenka.foodstorage.service;

import com.madzialenka.foodstorage.api.FoodRequestDTO;
import com.madzialenka.foodstorage.api.FoodResponseDTO;
import com.madzialenka.foodstorage.common.MeasurementUnit;
import com.madzialenka.foodstorage.exception.FoodNotFoundException;
import com.madzialenka.foodstorage.model.Food;
import com.madzialenka.foodstorage.repository.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FoodServiceImplTest {

    private static final String DEFAULT_SORT_BY = "id";
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
    private FoodService underTest;
    private FoodRepository foodRepository;

    @BeforeEach
    void setUp() {
        foodRepository = Mockito.mock(FoodRepository.class);
        underTest = new FoodServiceImpl(foodRepository);
    }

    @Test
    void getAllFood_withNotNullParams() {
        String sortBy = "field";
        Sort.Direction direction = Sort.Direction.DESC;
        String name = "name";

        List<Food> allFood = Arrays.asList(
                new Food(1L, "milk", MeasurementUnit.CARTON, 3L,
                        LocalDate.of(2022, Month.APRIL, 2)),
                new Food(2L, "fish", MeasurementUnit.PIECE, 20L,
                        LocalDate.of(2020, Month.DECEMBER, 16))
        );
        Mockito.when(foodRepository.findByNameStartingWith(name, Sort.by(direction, sortBy))).thenReturn(allFood);
        List<FoodResponseDTO> expected = Arrays.asList(
                new FoodResponseDTO(1L, "milk", MeasurementUnit.CARTON, 3L,
                        LocalDate.of(2022, Month.APRIL, 2)),
                new FoodResponseDTO(2L, "fish", MeasurementUnit.PIECE, 20L,
                        LocalDate.of(2020, Month.DECEMBER, 16))
        );

        List<FoodResponseDTO> actual = underTest.getAllFood(sortBy, direction, name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllFood_withNullParams() {
        String sortBy = null;
        Sort.Direction direction = null;
        String name = null;

        List<Food> allFood = Arrays.asList(
                new Food(1L, "milk", MeasurementUnit.CARTON, 3L,
                        LocalDate.of(2022, Month.APRIL, 2)),
                new Food(2L, "fish", MeasurementUnit.PIECE, 20L,
                        LocalDate.of(2020, Month.DECEMBER, 16))
        );
        Mockito.when(foodRepository.findAll(Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_BY))).thenReturn(allFood);
        List<FoodResponseDTO> expected = Arrays.asList(
                new FoodResponseDTO(1L, "milk", MeasurementUnit.CARTON, 3L,
                        LocalDate.of(2022, Month.APRIL, 2)),
                new FoodResponseDTO(2L, "fish", MeasurementUnit.PIECE, 20L,
                        LocalDate.of(2020, Month.DECEMBER, 16))
        );

        List<FoodResponseDTO> actual = underTest.getAllFood(sortBy, direction, name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addFood() {
        String name = "milk";
        MeasurementUnit measurementUnit = MeasurementUnit.CARTON;
        long amount = 4L;
        LocalDate bestBefore = LocalDate.of(2021, Month.SEPTEMBER, 15);
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO(name, measurementUnit, amount, bestBefore);
        long id = 1L;

        Food food = new Food(null, name, measurementUnit, amount, bestBefore);
        Food savedFood = new Food(id, name, measurementUnit, amount, bestBefore);
        Mockito.when(foodRepository.save(food)).thenReturn(savedFood);
        FoodResponseDTO expected = new FoodResponseDTO(id, name, measurementUnit, amount, bestBefore);

        FoodResponseDTO actual = underTest.addFood(foodRequestDTO);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateFood() {
        Long id = 1L;
        String name = "name";
        MeasurementUnit measurementUnit = MeasurementUnit.BOTTLE;
        Long amount = 3L;
        LocalDate bestBefore = LocalDate.of(2022, Month.DECEMBER, 23);
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO(name, measurementUnit, amount, bestBefore);

        Food food = new Food(id, "milk", MeasurementUnit.CARTON, 5L, LocalDate.now());
        Mockito.when(foodRepository.findById(id)).thenReturn(Optional.of(food));
        Food updatedFood = new Food(id, name, measurementUnit, amount, bestBefore);
        Mockito.when(foodRepository.save(updatedFood)).thenReturn(updatedFood);
        FoodResponseDTO expected = new FoodResponseDTO(id, name, measurementUnit, amount, bestBefore);

        FoodResponseDTO actual = underTest.updateFood(id, foodRequestDTO);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateFood_withThrownException() {
        Long id = 1L;
        FoodRequestDTO foodRequestDTO = Mockito.mock(FoodRequestDTO.class);

        Mockito.when(foodRepository.findById(id)).thenReturn(Optional.empty());

        FoodNotFoundException exception =
                assertThrows(FoodNotFoundException.class, () -> underTest.updateFood(id, foodRequestDTO));
        Assertions.assertEquals(String.format("Food with id: %d not found", id), exception.getMessage());
    }

    @Test
    void deleteFood() {
        Long id = 1L;
        String name = "name";
        MeasurementUnit measurementUnit = MeasurementUnit.BOTTLE;
        Long amount = 3L;
        LocalDate bestBefore = LocalDate.of(2022, Month.DECEMBER, 23);

        Food food = new Food(id, name, measurementUnit, amount, bestBefore);
        Mockito.when(foodRepository.findById(id)).thenReturn(Optional.of(food));
        FoodResponseDTO expected = new FoodResponseDTO(id, name, measurementUnit, amount, bestBefore);

        FoodResponseDTO actual = underTest.deleteFood(id);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(foodRepository).delete(food);
    }
}