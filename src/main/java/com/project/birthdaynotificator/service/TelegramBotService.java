package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.model.User;

import java.util.UUID;

public interface TelegramBotService {
    void connectAccount(long chatId, User userId);
}
