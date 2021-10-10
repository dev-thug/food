package com.management.food.assembler;

import com.management.food.controller.ApplicationController;
import com.management.food.controller.FoodController;
import com.management.food.entity.Application;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApplicationModelAssembler implements RepresentationModelAssembler<Application, EntityModel<Application>> {
    @Override
    public EntityModel<Application> toModel(Application entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ApplicationController.class).add(entity.getId())).withSelfRel()

        );
    }

}
