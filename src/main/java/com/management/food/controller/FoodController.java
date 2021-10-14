package com.management.food.controller;

import com.management.food.assembler.FoodModelAssembler;
import com.management.food.entity.Food;
import com.management.food.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Tag(name = "식단", description = "식단 API")
@RestController
@RequiredArgsConstructor
public class FoodController {

    private final PagedResourcesAssembler<Food> resourcesAssembler;
    private final FoodModelAssembler modelAssembler;
    private final FoodService foodService;

    @Operation(summary = "식단 상세 조회", description = "식단 id로 상세조회")
    @GetMapping(value = "/food/{id}")
    public EntityModel<Food> get(@PathVariable Long id) {
        return modelAssembler.toModel(foodService.get(id));
    }


    @Operation(summary = "식단 조회", description = "검색과 페이징이 포함된 식단조회")
    @GetMapping(value = "/food")
    public PagedModel<EntityModel<Food>> get(@ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) String search) {

        if (search != null) {
            return resourcesAssembler.toModel(foodService.get(search, pageable), modelAssembler);
        }
        return resourcesAssembler.toModel(foodService.get(pageable), modelAssembler);
    }


    @Secured("ROLE_ADMIN")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "실습 비용 수정", description = "식단 id에 대한 실습 비용을 수정")
    @PutMapping(value = "/food/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam int cost) {
        EntityModel<Food> entity = modelAssembler.toModel(foodService.updateCost(id, cost));
        return ResponseEntity.created(entity.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entity);
    }
}
