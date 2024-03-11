package com.project.birthdaynotificator.controller;

import com.project.birthdaynotificator.dto.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.NotificationResponse;
import com.project.birthdaynotificator.exception.BindingExceptions;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationRestController {

    private final NotificationService notificationService;
    @GetMapping("/{id:\\d+}")
    public NotificationResponse findNotification(@PathVariable int id) throws ModelNotFoundException {
        return NotificationResponse.getResponseFromModel(notificationService.find(id));
    }

    @PostMapping
    public NotificationResponse createNotification(@Valid @RequestBody CreateNotificationRequest request, BindingResult bindingResult) throws BindingExceptions {
        if(bindingResult.hasErrors()) throw new BindingExceptions(bindingResult.getAllErrors());
        return NotificationResponse.getResponseFromModel(notificationService.create(request));
    }
}
