package com.management.food.service;

import com.management.food.advice.exception.NotFoundResourceException;
import com.management.food.entity.Food;
import com.management.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public Food get(Long id) {
        return foodRepository.findById(id).orElseThrow(NotFoundResourceException::new);
    }

    public Page get(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public Food get(String name) {
        return foodRepository.findByName(name);
    }
}
