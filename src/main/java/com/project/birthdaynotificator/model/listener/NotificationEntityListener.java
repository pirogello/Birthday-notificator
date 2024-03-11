package com.project.birthdaynotificator.model.listener;

import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.model.NotificationPeriod;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class NotificationEntityListener {
    @PrePersist
    void onPrePersist(Notification notification) {
        notification.setPeriods(Set.of(NotificationPeriod.DAY));
    }
}
