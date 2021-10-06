package com.management.food.controller;

import com.management.food.config.security.TokenProvider;
import com.management.food.entity.User;
import com.management.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String signIn(@RequestParam String email, @RequestParam String password) {
        User user = userService.get(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "로그인 실패";
        }

        return tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable long id){
        return (User) userService.loadUserByUsername(String.valueOf(id));
    }

}
