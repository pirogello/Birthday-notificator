package com.project.birthdaynotificator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public void addTelegramChat(Long chatId, UUID userId) {
        log.info("Добавление чата с id=%s для пользователя с id=%s".formatted(chatId, userId));
    }
}
