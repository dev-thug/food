package com.management.food.assembler;

import com.management.food.entity.Application;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ApplicationModelAssembler implements RepresentationModelAssembler<Application, EntityModel<Application>> {
    @Override
    public EntityModel<Application> toModel(Application entity) {
        return null;
    }

}
