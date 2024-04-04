package com.project.birthdaynotificator.util.telegram;

import java.util.UUID;

public interface TelegramConnectAccountHelper {
    void sendConfirmationForm(long chatId, UUID userId, String username);
}
