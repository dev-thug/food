package com.management.food.assembler;

import com.management.food.controller.FoodController;
import com.management.food.controller.LectureController;
import com.management.food.entity.Lecture;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LectureModelAssembler implements RepresentationModelAssembler<Lecture, EntityModel<Lecture>> {
    @Override
    public EntityModel<Lecture> toModel(Lecture entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(LectureController.class).get(entity.getId())).withSelfRel()
        );
    }
}
