package com.project.birthdaynotificator.util.telegram;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import static com.project.birthdaynotificator.util.telegram.BotCommandsEnum.*;

import java.util.List;

public interface BotCommands {

    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand(START.getCommand(), "start bot"),
            new BotCommand(HELP.getCommand(), "bot info"),
            new BotCommand(CONNECT_ACCOUNT.getCommand(), "connect account")
    );

    String HELP_TEXT = """
            Этот бот напомнит тебе, когда день рождения у твоих друзей.
            Для этого доступны следующие команды:
            %s - старт
            %s - помощь
            %s - подключить аккаунт
            """.formatted(START.getCommand(),
                          HELP.getCommand(),
                          CONNECT_ACCOUNT.getCommand());

    String CONNECT_ACCOUNT_TEXT = """
            Для подключения аккаунта нажмите на кнопку ниже.
           """;
    String  CONFIRM_CONNECTION_ACCOUNT_TEXT = """
             Аккаунт пользователя "%s" подключен к этому боту""";

    String NOTIFICATION_INFO_TEXT = """
            Через %s дней у кого-то день рождение)))
            
            Когда: %s
            У кого: %s
            Исполняется: %s лет
            
            Напоминаем за: %s дней
            """;
}
