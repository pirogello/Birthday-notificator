package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.dto.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.NotificationResponse;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepo;

    @Override
    public Notification find(int id) throws ModelNotFoundException {
        return notificationRepo.findById(id)
                .orElseThrow(()-> new ModelNotFoundException("Напоминание с id=%d не найдено".formatted(id)));
    }

    @Override
    public Notification create(CreateNotificationRequest request) {
        Notification notification = getModelFromCreateRequest(request);
        return notificationRepo.save(notification);
    }

    private Notification getModelFromCreateRequest(CreateNotificationRequest request){
        Notification notification = new Notification();
        notification.setDetails(request.getDetails());
        notification.setBirthdayDate(request.getBirthdayDate());
        return notification;
    }
}
