package com.project.birthdaynotificator.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidExceptionBody {
    private String object;
    private String message;
    private String rejectedValue;
    private String fields;

}