package com.management.food.controller;

import com.management.food.entity.Application;
import com.management.food.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Tag(name = "수강 신청", description = "수강 신청 API")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "수강 신청", description = "수강 신청을 합니다.")
    @PostMapping(value = "/application")
    public Application add(@RequestParam Long id) {
        return applicationService.add(id);
    }

    // Fixme 수강 신청후 입금, 환불 , 입금을 많이 했을때, 적게 했을때 기능필요
//    @Operation(summary = "수강 취소", description = "수강 취소를 합니다.")
//    @DeleteMapping(value = "/application/{id}/cancel")
//    public Application cancel(@PathVariable Long id) {
//        return applicationService.cancel(id);
//    }
//
//    @Operation(summary = "실습 비용 입금", description = "실습 비용을 입금합니다..")
//    @PutMapping(value = "/application/{id}/complete")
//    public Application complete(@PathVariable Long id) {
//        return applicationService.complete(id);
//    }
//
//    @Operation(summary = "실습 비용 환불", description = "수강 취소를 합니다.")
//    @DeleteMapping(value = "/application/{id}/refund")
//    public Application refund(@PathVariable Long id) {
//        return applicationService.refund(id);
//    }
}
