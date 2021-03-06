package com.management.food.controller;

import com.management.food.assembler.LectureModelAssembler;
import com.management.food.dto.LectureDTO;
import com.management.food.entity.Lecture;
import com.management.food.service.FoodService;
import com.management.food.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Tag(name = "강의", description = "강의 API")
@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final LectureModelAssembler modelAssembler;
    private final PagedResourcesAssembler<Lecture> resourcesAssembler;

    @Operation(summary = "강의 조회", description = "개설된 강의를 조회합니다..")
    @GetMapping(value = "/lecture")
    public PagedModel<EntityModel<Lecture>> get(@ParameterObject @PageableDefault(direction = Sort.Direction.DESC, sort = {"dateAt", "fromAt"}) Pageable pageable, @RequestParam(required = false) String search) {
        if (search != null) {
            return resourcesAssembler.toModel(lectureService.get(search, pageable), modelAssembler);
        }
        return resourcesAssembler.toModel(lectureService.get(pageable), modelAssembler);
    }

    @Operation(summary = "강의 상세 조회", description = "개설된 강의를 상세 조회합니다.")
    @GetMapping(value = "/lecture/{id}")
    public EntityModel<Lecture> get(@PathVariable Long id) {
        return modelAssembler.toModel(lectureService.get(id));
    }

    @Secured("ROLE_ADMIN")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "강의 등록", description = "새로운 강의를 등록합니다.")
    @PostMapping(value = "/lecture")
    public EntityModel<Lecture> add(@ParameterObject @ModelAttribute LectureDTO lectureDTO) {
        return modelAssembler.toModel(lectureService.add(lectureDTO));
    }

    @Secured("ROLE_ADMIN")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "강의 수정", description = "강의를 수정합니다.")
    @PutMapping(value = "/lecture/{id}")
    public EntityModel<Lecture> update(@PathVariable Long id, @ParameterObject @ModelAttribute LectureDTO lectureDTO) {
        return modelAssembler.toModel(lectureService.update(id, lectureDTO));
    }


}
