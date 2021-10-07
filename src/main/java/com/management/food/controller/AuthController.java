package com.management.food.controller;

import com.management.food.advice.exception.InvalidPasswordException;
import com.management.food.config.security.TokenProvider;
import com.management.food.dto.UserDTO;
import com.management.food.entity.User;
import com.management.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
public class AuthController {


    private final UserService userService;

    @PostMapping("/signin")
    public String signIn(@RequestParam String email, @RequestParam String password) {
        return userService.getToken(email, password);
    }

    @PostMapping(value = "/signup")
    public User signUp(@ParameterObject @ModelAttribute UserDTO userDTO) {
        return userService.add(userDTO);
    }


}
