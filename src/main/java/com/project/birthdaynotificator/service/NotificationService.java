package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.dto.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.UpdateNotificationRequest;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.model.NotificationPeriod;

import java.time.LocalDate;
import java.util.List;

public interface NotificationService {
    Notification find(int id) throws ModelNotFoundException;
    Notification create(CreateNotificationRequest request);
    Notification update(UpdateNotificationRequest request) throws ModelNotFoundException;
    List<Notification> getAllBetween(LocalDate from, LocalDate to);

    List<NotificationPeriod> getAllAvailablePeriods();
}
