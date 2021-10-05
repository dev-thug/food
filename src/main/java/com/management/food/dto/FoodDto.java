package com.management.food.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDto {

    String name;

    String ingredients;

    String image;

    Integer cost;


}
