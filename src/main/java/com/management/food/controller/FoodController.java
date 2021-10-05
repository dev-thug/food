package com.management.food.controller;

import com.management.food.dto.FoodDto;
import com.management.food.entity.Food;
import com.management.food.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "식단", description = "식단 API")
@RestController
@RequiredArgsConstructor
public class FoodController {

    private ModelMapper modelMapper = new ModelMapper();

    private final FoodService foodService;

    @Operation(summary = "식단 상세 조회", description = "식단 id로 상세조회")
    @GetMapping(value = "/food/{id}")
    public FoodDto get(@Parameter(name = "식단 코드", description = "유효한 식단 코드", in = ParameterIn.PATH) @PathVariable Long id) {
        return modelMapper.map(foodService.get(id), FoodDto.class);
    }

    @Operation(summary = "식단 조회", description = "검색과 페이징이 포함된 식단조회")
    @GetMapping(value = "/food")
    public Page<Food> get(@ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable, @Parameter(name = "식단 이름", description = "단어가 포함된 식단의 이름을 조회한다") @RequestParam(required = false) String search) {
        System.out.println(search);
        if (search != null) {
            return foodService.get(search, pageable);
        }
        return foodService.get(pageable);
    }

    @Operation(summary = "실습 비용 수정", description = "식단 id에 대한 실습 비용을 수정")
    @PutMapping(value = "/food/{id}")
    public Food update(@PathVariable Long id, @Parameter(name = "실습비", description = "실습 비용을 수정 한다.") @RequestParam int cost) {
        return foodService.updateCost(id, cost);
    }

//    private FoodDto convertToDto(Food food) {
//        FoodDto foodDto = modelMapper.map(food, FoodDto.class);
//
//        return foodDto;
//    }
//
//    private Food convertToEntity(FoodDto foodDto) {
//
//        // foodDto의 id 값이 있을 경우
//        if (foodDto.getId() != null) {
//
//            return foodService.get(foodDto.getId());
//        }
//
//        // foodDto의 id 값이 없을 경우
//        Food food = modelMapper.map(foodDto, Food.class);
//
//
//        return food;
//    }
}
