package com.management.food.service;

import com.management.food.advice.exception.NotFoundUserException;
import com.management.food.entity.User;
import com.management.food.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(userId)).orElseThrow(NotFoundUserException::new);
    }

    public User get(String email) {
        return userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }
}
