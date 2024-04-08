package com.project.birthdaynotificator.service.impl;

import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.repository.NotificationRepository;
import com.project.birthdaynotificator.service.NotificationService;
import com.project.birthdaynotificator.util.Notificator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificatorService {

    private final NotificationRepository notificationRepository;
    @Qualifier("logerNotificator") @NonNull
    private final Notificator logerNotificator;
    @Qualifier("telegramNotificator") @NonNull
    private final Notificator telegramNotificator;

    @Async
    @Scheduled(cron="0 0-59 * * * *")// каждую минуту
    //@Scheduled(cron="0 0 9 * * *")// каждый день в 9:00
    public void notificate() {
        LocalDate now = LocalDate.now();
        List<Notification> upcomingNotifications = notificationRepository
                .findAllByBirthdayDateWithoutYearBetween(now.plusDays(1).getMonthValue(), now.plusDays(1).getDayOfMonth(),
                                                         now.plusDays(7).getMonthValue(), now.plusDays(7).getDayOfMonth());
        upcomingNotifications.forEach(n-> {
            n.getPeriods().forEach(p -> {
                var BDWithPeriod = n.getBirthdayDate().minusDays(p.getValue());
                if(now.getMonthValue() == BDWithPeriod.getMonthValue()
                && now.getDayOfMonth() == BDWithPeriod.getDayOfMonth()){
                    // TODO insert notificators for send notifications
                    logerNotificator.sendNotification(n);
                    telegramNotificator.sendNotification(n);
                }

            });
        });
    }
}
