package com.management.food.repository;

import com.management.food.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findByNameContaining(String name, Pageable pageable);
}
