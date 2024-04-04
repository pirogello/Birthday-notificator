package com.project.birthdaynotificator.util.telegram;

import com.project.birthdaynotificator.config.TelegramBotConfig;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.UUID;

import static com.project.birthdaynotificator.util.telegram.BotCommandsEnum.*;

@Getter
public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Старт");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Помощь");
    private static final InlineKeyboardButton CONNECT_ACCOUNT_BUTTON = new InlineKeyboardButton("Подключить аккаунт");
    private static final InlineKeyboardButton CONFIRMATION_CONNECT_ACCOUNT_BUTTON = new InlineKeyboardButton("Подтвердить");

    public static InlineKeyboardMarkup inlineMarkup() {
        START_BUTTON.setCallbackData(START.getCommand());
        HELP_BUTTON.setCallbackData(HELP.getCommand());

        List<InlineKeyboardButton> rowInline = List.of(START_BUTTON, HELP_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup inlineConnectConfirmationButton(UUID userId) {
        CONFIRMATION_CONNECT_ACCOUNT_BUTTON.setCallbackData(CONFIRM_CONNECT_ACCOUNT.getCommand() + " " + userId);
        List<InlineKeyboardButton> rowInline = List.of(CONFIRMATION_CONNECT_ACCOUNT_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup inlineConnectButton(long chatId) {
        CONNECT_ACCOUNT_BUTTON.setUrl("%s/api/v1/telegram/connect/chat/%s".formatted(TelegramBotConfig.connectionHost, chatId));
        List<InlineKeyboardButton> rowInline = List.of(CONNECT_ACCOUNT_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
}
