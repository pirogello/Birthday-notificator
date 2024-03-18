package com.project.birthdaynotificator.util;

import com.project.birthdaynotificator.dto.CreateNotificationRequest;
import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component("LogerNotificator")
@Slf4j
@RequiredArgsConstructor
public class LogerNotificator implements Notificator, CommandLineRunner {

    private final NotificationService notificationService;
    @Override
    public void sendNotifications(Notification notification) {
        LocalDate now = LocalDate.now();
        int yearsOld = now.getYear() - notification.getBirthdayDate().getYear();
        int daysToBD = getDiffInDaysOfTwoDates(now, notification.getBirthdayDate());
        String years = yearsOld % 10 == 1 ? "год" : ((yearsOld % 10 < 5) ? "года" : "лет");
        log.info("Через %d день День рождения у %s. Исполняется %d %s\n".formatted(daysToBD, notification.getDetails(), yearsOld, years));
    }


    private int getDiffInDaysOfTwoDates(LocalDate date1, LocalDate date2){
        int arbitraryYear = 2000;
        LocalDate date1WithoutYear = LocalDate.of(arbitraryYear, date1.getMonth(), date1.getDayOfMonth());
        LocalDate date2WithoutYear = LocalDate.of(arbitraryYear, date2.getMonth(), date2.getDayOfMonth());
        int differenceInDays = Period.between(date1WithoutYear, date2WithoutYear).getDays();
        if (differenceInDays < 0) {
            differenceInDays += date1WithoutYear.lengthOfYear();
        }
        return differenceInDays;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating new notification`s");
        CreateNotificationRequest createNotificationRequest1 = new CreateNotificationRequest("Details", LocalDate.now().plusDays(1));
        CreateNotificationRequest createNotificationRequest3 = new CreateNotificationRequest("Details", LocalDate.now().plusDays(3));
        CreateNotificationRequest createNotificationRequest7 = new CreateNotificationRequest("Details", LocalDate.now().plusDays(7));
        notificationService.create(createNotificationRequest1);
        notificationService.create(createNotificationRequest3);
        notificationService.create(createNotificationRequest7);
    }
}
