package com.project.birthdaynotificator.service.impl;


import com.project.birthdaynotificator.model.User;
import com.project.birthdaynotificator.repository.UserRepository;
import com.project.birthdaynotificator.service.TelegramBotService;
import com.project.birthdaynotificator.util.telegram.TelegramConnectAccountHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramBotServiceImpl implements TelegramBotService {

    private final UserRepository userRepository;
    private final TelegramConnectAccountHelper telegramConnectAccountHelper;
    @Override
    public void connectAccount(long chatId, User user) {
        user.getTelegramChatIds().add(chatId);
        userRepository.save(user);
        telegramConnectAccountHelper.sendConfirmation(chatId, user.getId(), user.getUsername());
        log.info("Аккаунт пользователя %s подключен к чату в телеграм с id=%s".formatted(user.getUsername(), chatId));
    }
}
