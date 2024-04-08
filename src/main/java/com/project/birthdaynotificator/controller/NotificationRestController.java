package com.project.birthdaynotificator.controller;

import com.project.birthdaynotificator.dto.request.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.response.NotificationResponse;
import com.project.birthdaynotificator.dto.response.PeriodsResponse;
import com.project.birthdaynotificator.dto.request.UpdateNotificationRequest;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.model.NotificationPeriod;
import com.project.birthdaynotificator.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3001")
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
    public NotificationResponse createNotification(@Valid @RequestBody CreateNotificationRequest request) throws Exception {
        return NotificationResponse.getResponseFromModel(notificationService.create(request));
    }

    @PutMapping
    public NotificationResponse updateNotification(@Valid @RequestBody UpdateNotificationRequest request) throws ModelNotFoundException {
        return NotificationResponse.getResponseFromModel(notificationService.update(request));
    }

    @GetMapping
    public List<NotificationResponse> getNotifications(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                       LocalDate from,
                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                       LocalDate to){
        return notificationService.getAllBetween(from, to).stream()
                .map(NotificationResponse::getResponseFromModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/periods")
    public PeriodsResponse getAllAvailablePeriods() {
        return new PeriodsResponse(notificationService.getAllAvailablePeriods().stream()
                .map(NotificationPeriod::getValue)
                .collect(Collectors.toList()));
    }


}
