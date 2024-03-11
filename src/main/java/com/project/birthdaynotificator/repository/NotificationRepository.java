package com.project.birthdaynotificator.repository;

import com.project.birthdaynotificator.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    @Query("""
            select n from Notification n where
            EXTRACT(MONTH FROM n.birthdayDate) > :startMonth
            or (
                EXTRACT(MONTH FROM n.birthdayDate) = :startMonth
                and EXTRACT(DAY FROM n.birthdayDate) >= :startDay
            )
            and (
                EXTRACT(MONTH FROM n.birthdayDate) < :endMonth
                or (
                    EXTRACT(MONTH FROM n.birthdayDate) = :endMonth
                    and EXTRACT(DAY FROM n.birthdayDate) <= :endDay
                )
                )
            """)
List<Notification> findAllByBirthdayDateWithoutYearBetween(@Param("startMonth") int startMonth, @Param("startDay") int startDay,
                                                           @Param("endMonth") int endMonth, @Param("endDay") int endDay);

}
