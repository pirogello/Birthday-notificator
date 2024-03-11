package com.project.birthdaynotificator.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationPeriod {
    DAY(1),
    DAY3(3),
    WEEK(7);

    private final int value;

    NotificationPeriod(int value) {
        this.value = value;
    }
    @JsonValue
    public int getValue() {
        return value;
    }
}
