package com.project.birthdaynotificator.service.impl;

import com.project.birthdaynotificator.dto.request.CreateNotificationRequest;
import com.project.birthdaynotificator.dto.request.UpdateNotificationRequest;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.model.NotificationPeriod;
import com.project.birthdaynotificator.repository.NotificationRepository;
import com.project.birthdaynotificator.repository.UserRepository;
import com.project.birthdaynotificator.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepo;
    private final UserRepository userRepository;

    @Override
    public Notification find(int id) throws ModelNotFoundException {
        return notificationRepo.findById(id)
                .orElseThrow(()-> new ModelNotFoundException("Напоминание с id=%d не найдено".formatted(id)));
    }

    @Override
    public Notification create(CreateNotificationRequest request) throws Exception {
        Notification notification = getModelFromCreateRequest(request);
        return notificationRepo.save(notification);
    }

    @Transactional
    @Override
    public Notification update(UpdateNotificationRequest request) throws ModelNotFoundException {
          Notification notification = notificationRepo.findById(request.getId())
                  .orElseThrow(()-> new ModelNotFoundException("Напоминание с id=%d не найдено".formatted(request.getId())));
          Set<NotificationPeriod> periods = this.getPeriodsFromIntegerList(request.getPeriods());
          notification.setPeriods(periods);
          notification.setBirthdayDate(request.getBirthdayDate());
          notification.setDetails(request.getDetails());
          return notificationRepo.save(notification);
    }

    @Override
    public List<Notification> getAllBetween(LocalDate from, LocalDate to) {
        if(from == null) from = LocalDate.of(2000, Month.JANUARY,1);
        if(to == null) to = LocalDate.of(2000, Month.DECEMBER,31);
        return notificationRepo.findAllByBirthdayDateWithoutYearBetween(from.getMonthValue(), from.getDayOfMonth(),
                                                                        to.getDayOfMonth(), from.getDayOfMonth());
    }

    @Override
    public List<NotificationPeriod> getAllAvailablePeriods() {
        return Arrays.asList(NotificationPeriod.values());
    }

    private Notification getModelFromCreateRequest(CreateNotificationRequest request) throws Exception {
        Notification notification = new Notification();
        notification.setDetails(request.getDetails());
        notification.setBirthdayDate(request.getBirthdayDate());
        var user = userRepository.findById(request.getUserId()).orElseThrow(()->new Exception("User with id not found"));
        user.addNotification(notification);
        return notification;
    }


    private Set<NotificationPeriod> getPeriodsFromIntegerList(Set<Integer> periodValues) {
        return periodValues.stream()
                .map(value -> {
                    for (NotificationPeriod period : NotificationPeriod.values()) {
                        if (period.getValue() == value) {
                            return period;
                        }
                    }
                    throw new IllegalArgumentException("Неизвестный период уведомления с значением: " + value);
                })
                .collect(Collectors.toSet());
    }
}
