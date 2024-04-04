package com.project.birthdaynotificator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.model.NotificationPeriod;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
public class NotificationResponse {
    private Integer id;
    private String details;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdayDate;
    private Set<NotificationPeriod> periods;

    public static NotificationResponse getResponseFromModel(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .birthdayDate(notification.getBirthdayDate())
                .details(notification.getDetails())
                .periods(notification.getPeriods().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)))
                .build();
    }
}
