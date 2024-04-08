package com.project.birthdaynotificator.controller;

import com.project.birthdaynotificator.service.TelegramBotService;
import com.project.birthdaynotificator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telegram")
@RequiredArgsConstructor
public class TelegramRestController {

    private final TelegramBotService telegramBotService;
    private final UserService userService;

    @GetMapping("/connect/chat/{chatId}")
    public String connectAccountToBot(@PathVariable int chatId) {
        var user = userService.getCurrentUser();
        telegramBotService.connectAccount(chatId, user);
        return "Подключение бота к чату подтверждено";
    }
}
