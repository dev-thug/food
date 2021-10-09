package com.management.food.service;

import com.management.food.advice.exception.InvalidPasswordException;
import com.management.food.advice.exception.NotFoundEmailException;
import com.management.food.advice.exception.NotFoundUserException;
import com.management.food.config.security.TokenProvider;
import com.management.food.dto.UserDTO;
import com.management.food.entity.User;
import com.management.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User getAuthedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }

    public User get() {
        return getAuthedUser();
    }

    public User get(String email) {
        return userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

    public String getToken(String email, String password) {
        User user = this.get(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return tokenProvider.createToken(String.valueOf(user.getId()), user.getRoles());
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public User add(UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .roles(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
        return this.add(user);
    }

    public Page<User> get(SimpleGrantedAuthority role, Pageable pageable) {
        return userRepository.findAllByRolesContaining(role, pageable);
    }
}
