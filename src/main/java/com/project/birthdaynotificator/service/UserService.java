package com.project.birthdaynotificator.service;

import java.util.UUID;

public interface UserService {
    void addTelegramChat(Long chatId, UUID userId);
}
