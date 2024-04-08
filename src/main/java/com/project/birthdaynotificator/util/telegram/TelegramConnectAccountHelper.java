package com.project.birthdaynotificator.util.telegram;

public interface TelegramConnectAccountHelper {
    void sendConfirmation(long chatId, long userId, String username);
}
