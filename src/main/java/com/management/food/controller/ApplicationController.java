package com.management.food.controller;

import com.management.food.assembler.ApplicationModelAssembler;
import com.management.food.entity.Application;
import com.management.food.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "수강 신청", description = "수강 신청 API")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationModelAssembler modelAssembler;
    private final PagedResourcesAssembler<Application> resourcesAssembler;

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "수강 신청", description = "수강 신청을 합니다.")
    @PostMapping(value = "/application")
    public EntityModel<Application> add(@RequestParam Long lectureId) {
        return modelAssembler.toModel(applicationService.add(lectureId));
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "수강 내역 확인", description = "회원이 수강 신청한 내역을 확인합니다.")
    @GetMapping(value = "/application")
    public PagedModel<EntityModel<Application>> get(@ParameterObject @PageableDefault(sort = "applyAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return resourcesAssembler.toModel(applicationService.findByUser(pageable), modelAssembler);
    }


    // Fixme 정상 작동 되지 않음
    @Secured("ROLE_ADMIN")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "강의별 수강 내역 확인", description = "관리자가 강의에 수강 신청한 회원을 조회 합니다..")
    @GetMapping(value = "/application/{lectorId}")
    public PagedModel<EntityModel<Application>> get(@PathVariable Long lectorId, @ParameterObject @PageableDefault(sort = "applyAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return resourcesAssembler.toModel(applicationService.findByLecture(lectorId, pageable), modelAssembler);
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "수강 취소", description = "수강 취소를 합니다.")
    @DeleteMapping(value = "/application/{id}/cancel")
    public EntityModel<Application> cancel(@PathVariable Long id) {
        return modelAssembler.toModel(applicationService.cancel(id));
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "실습 비용 입금", description = "실습 비용을 입금합니다..")
    @PutMapping(value = "/application/{id}/complete")
    public EntityModel<Application> complete(@PathVariable Long id, @RequestParam int cost) {
        return modelAssembler.toModel(applicationService.complete(id, cost));
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "실습 비용 환불", description = "수강 취소를 합니다.")
    @DeleteMapping(value = "/application/{id}/refund")
    public EntityModel<Application> refund(@PathVariable Long id) {
        return modelAssembler.toModel(applicationService.refund(id));
    }
}
