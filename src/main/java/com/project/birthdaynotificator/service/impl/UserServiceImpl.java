package com.project.birthdaynotificator.service.impl;

import com.project.birthdaynotificator.dto.request.CreateUserRequest;
import com.project.birthdaynotificator.model.User;
import com.project.birthdaynotificator.repository.UserRepository;
import com.project.birthdaynotificator.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(CreateUserRequest dtoUser) throws Exception {
        if(userRepository.existsByUsername(dtoUser.getUsername())) throw new Exception("User with username = %s already exist");
        User user = new User();
        user.setUsername(dtoUser.getUsername());
        user.setPassword(dtoUser.getPassword());
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
