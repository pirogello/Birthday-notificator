package com.project.birthdaynotificator.service;


import com.project.birthdaynotificator.util.telegram.TelegramConnectAccountHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramBotServiceImpl implements TelegramBotService {

    private final TelegramConnectAccountHelper telegramConnectAccountHelper;
    @Override
    public void connectAccount(long chatId, UUID userId) {
        // TODO получить пользователя из БД
        String username = "username";

        telegramConnectAccountHelper.sendConfirmationForm(chatId, userId, username);
        log.info("Подключение акканта к телеграм боту");
    }
}
