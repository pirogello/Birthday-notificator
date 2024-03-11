package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.dto.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.NotificationResponse;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.model.Notification;

public interface NotificationService {
    Notification find(int id) throws ModelNotFoundException;
    Notification create(CreateNotificationRequest request);

}
