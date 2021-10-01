package com.management.food.controller;

import com.management.food.dto.FoodDto;
import com.management.food.entity.Food;
import com.management.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FoodController {

    private ModelMapper modelMapper = new ModelMapper();

    private final FoodService foodService;

    @GetMapping(value = "/food/{id}")
    public FoodDto get(@PathVariable Long id) {
        return modelMapper.map(foodService.get(id), FoodDto.class);
    }

    @GetMapping(value = "/food")
    public Page<Food> get(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return foodService.get(pageable);
    }

    @GetMapping(value = "/food/search/{name}")
    public Page<Food> get(@PathVariable String name,
                          @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return foodService.get(name, pageable);
    }

    @PutMapping(value = "/food/{id}")
    public Food update(@PathVariable Long id, @RequestParam int cost) {
        return foodService.updateCost(id, cost);
    }

    private FoodDto convertToDto(Food food) {
        FoodDto foodDto = modelMapper.map(food, FoodDto.class);

        return foodDto;
    }

    private Food convertToEntity(FoodDto foodDto) {

        // foodDto의 id 값이 있을 경우
        if (foodDto.getId() != null) {

            return foodService.get(foodDto.getId());
        }

        // foodDto의 id 값이 없을 경우
        Food food = modelMapper.map(foodDto, Food.class);


        return food;
    }
}
