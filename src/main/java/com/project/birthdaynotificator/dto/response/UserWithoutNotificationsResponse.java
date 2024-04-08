package com.project.birthdaynotificator.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithoutNotificationsResponse {
    private Long id;
    private String username;
}
