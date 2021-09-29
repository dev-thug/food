package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Food;
import com.management.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public Food get(Long id) {
        return foodRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    public Page<Food> get(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public Page<Food> get(String name, Pageable pageable) {
        return foodRepository.findByNameContaining(name, pageable);
    }

    @Transactional
    public Food updateCost(Long id, Integer cost){
        return get(id).updateCost(cost);
    }
}
