package com.management.food.controller;

import com.management.food.entity.Food;
import com.management.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping(value = "/food/{id}")
    public Food get(@PathVariable Long id) {
        return foodService.get(id);
    }

    @GetMapping(value = "/food")
    public Page get(@PageableDefault(sort = "name",direction = Sort.Direction.ASC) Pageable pageable) {
        return foodService.get(pageable);
    }
}
