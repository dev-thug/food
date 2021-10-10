package com.management.food.controller;

import com.management.food.entity.User;
import com.management.food.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원", description = "회원 API")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Secured("ROLE_ADMIN")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "회원 조회", description = "관리자가 id로 회원정보를 조회합니다.")
    @GetMapping(value = "/user/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "회원 조회", description = "자신의 회원정보를 조회합니다.")
    @GetMapping(value = "/me")
    public User get() {
        return userService.get();
    }


    @GetMapping(value = "/user")
    public Page get(String role, Pageable pageable) {
        return userService.get(new SimpleGrantedAuthority(role), pageable);
    }

    @Secured("ROLE_USER")
    @Parameter(name = "AUTH-TOKEN", in = ParameterIn.HEADER, description = "인증을 위한 JWT 토큰입니다", required = true)
    @Operation(summary = "회원 포인트 추가", description = "포인트를 추가한다..")
    @PostMapping(value = "/point")
    public User addPoint(@RequestParam int point) {
        return userService.addPoint(point);
    }

}
