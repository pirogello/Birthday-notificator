package com.project.birthdaynotificator.model;

import com.project.birthdaynotificator.model.listener.NotificationEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "notification")
@EntityListeners(NotificationEntityListener.class)
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String details;

    @NotNull
    private LocalDate birthdayDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="periods", joinColumns=@JoinColumn(name="notification_id"))
    @Column(name="c_period")
    private Set<NotificationPeriod> periods;
}
