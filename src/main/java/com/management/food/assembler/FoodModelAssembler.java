package com.management.food.assembler;

import com.management.food.controller.FoodController;
import com.management.food.entity.Food;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FoodModelAssembler implements RepresentationModelAssembler<Food, EntityModel<Food>> {

    @Override
    public EntityModel<Food> toModel(Food entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(FoodController.class).get(entity.getId())).withSelfRel(),
                linkTo(methodOn(FoodController.class).update(entity.getId(), entity.getCost())).withRel("put")
        );
    }

}
