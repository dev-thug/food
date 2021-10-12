package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.config.CacheKey;
import com.management.food.entity.Food;
import com.management.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    @Cacheable(value = CacheKey.FOOD, key = "#id", unless = "#result == null")
    public Food get(Long id) {
        return foodRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

//    @Cacheable(value = CacheKey.FOODS, key = "#pageable.pageNumber", unless = "#result == null")
    public Page<Food> get(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

//    @Cacheable(value = CacheKey.FOODS, key = "#name", unless = "#result == null")
    public Page<Food> get(String name, Pageable pageable) {
        return foodRepository.findByNameContaining(name, pageable);
    }

    @CachePut(value = CacheKey.FOOD, key = "#id")
    @Transactional
    public Food updateCost(Long id, Integer cost) {
        return get(id).updateCost(cost);
    }
}
