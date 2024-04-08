package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.dto.request.CreateUserRequest;
import com.project.birthdaynotificator.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {

    User createUser(CreateUserRequest dtoUser) throws Exception;
    User getByUsername(String username);
    User getCurrentUser();

    UserDetailsService userDetailsService();
}
