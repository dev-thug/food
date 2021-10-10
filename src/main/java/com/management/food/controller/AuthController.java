package com.management.food.controller;

import com.management.food.dto.UserDTO;
import com.management.food.entity.User;
import com.management.food.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Tag(name = "회원 인증", description = "회원 인증 API")
@RequiredArgsConstructor
@RestController
public class AuthController {


    private final UserService userService;

    @Operation(summary = "회원 로그인", description = "회원이 로그인후 인증 토큰을 발행받습니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
        Map<String, String> map = new HashMap<>();
        map.put("auth-token", userService.getToken(email, password));
        return ResponseEntity.accepted().body(map);
    }

    @Operation(summary = "회원 가입", description = "회원가입을 합니다..")
    @PostMapping(value = "/signup")
    public User signUp(@ParameterObject @ModelAttribute UserDTO userDTO) {
        return userService.add(userDTO);
    }


}
