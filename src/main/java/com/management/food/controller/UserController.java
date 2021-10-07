package com.management.food.controller;

import com.management.food.entity.User;
import com.management.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user/{id}")
    public User get(@PathVariable Long id){
        return userService.get(id);
    }

    @GetMapping(value = "/user")
    public Page get(String role, Pageable pageable){
        return userService.get(new SimpleGrantedAuthority(role), pageable);
    }

}
