package com.madzialenka.foodstorage.repository;

import com.madzialenka.foodstorage.model.Food;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByNameStartingWith(String name, Sort sort);
}
