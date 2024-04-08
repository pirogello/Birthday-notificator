package com.project.birthdaynotificator.service;

import com.project.birthdaynotificator.model.User;

public interface TelegramBotService {
    void connectAccount(long chatId, User userId);
}
