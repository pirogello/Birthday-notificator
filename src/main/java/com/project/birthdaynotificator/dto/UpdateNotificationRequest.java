package com.project.birthdaynotificator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNotificationRequest {

    @NotNull(message = "Id не может быть пустым")
    private Integer id;
    private String details;

    @NotNull(message = "birthdayDate не может быть пустым")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdayDate;

    private Set<Integer> periods;
}
