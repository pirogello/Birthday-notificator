package com.project.birthdaynotificator.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtAuthenticationResponse {
    private String jwtToken;
}
