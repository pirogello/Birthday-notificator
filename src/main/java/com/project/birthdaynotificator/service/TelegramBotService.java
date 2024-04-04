package com.project.birthdaynotificator.service;

import java.util.UUID;

public interface TelegramBotService {
    void connectAccount(long chatId, UUID userId);
}
