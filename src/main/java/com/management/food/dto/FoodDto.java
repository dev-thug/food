package com.management.food.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {
    Long id;

    String name;

    String ingredients;

    String image;

    Integer cost;

}
