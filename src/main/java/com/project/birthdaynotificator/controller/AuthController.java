package com.project.birthdaynotificator.controller;


import com.project.birthdaynotificator.dto.request.CreateUserRequest;
import com.project.birthdaynotificator.dto.request.LoginRequest;
import com.project.birthdaynotificator.dto.response.JwtAuthenticationResponse;
import com.project.birthdaynotificator.model.User;
import com.project.birthdaynotificator.service.UserService;
import com.project.birthdaynotificator.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public JwtAuthenticationResponse create(@RequestBody CreateUserRequest dto) throws Exception {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userService.createUser(dto);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest dto) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        ));
        var user = userService.userDetailsService().loadUserByUsername(dto.getUsername());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
