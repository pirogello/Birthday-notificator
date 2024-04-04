package com.project.birthdaynotificator.util;

import com.project.birthdaynotificator.model.Notification;
import com.project.birthdaynotificator.service.UserService;
import com.project.birthdaynotificator.util.telegram.BotCommands;
import com.project.birthdaynotificator.util.telegram.Buttons;
import com.project.birthdaynotificator.util.telegram.TelegramConnectAccountHelper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static com.project.birthdaynotificator.util.telegram.BotCommandsEnum.*;

@Component("telegramNotificator")
@Getter
@Slf4j
public class TelegramNotificator extends TelegramLongPollingBot implements Notificator, TelegramConnectAccountHelper, BotCommands {
    private final String botToken;
    private final String botUsername;
    private final UserService userService;

    public TelegramNotificator(TelegramBotsApi telegramBotsApi,
                               @Value("${telegram.bot.token}") String botToken,
                               @Value("${telegram.bot.name}") String botUsername,
                               UserService userService) throws TelegramApiException {
        super(botToken);
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.userService = userService;
        telegramBotsApi.registerBot(this);
        this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        long chatId = 0;
        long userId = 0;
        String userName;
        String receivedMessage;

        if(update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }

        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();
            botAnswerUtils(receivedMessage, chatId, userName);
        }

    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        if(Objects.equals(receivedMessage, START.getCommand())){
            startBot(chatId, userName); return;
        }
        if(receivedMessage.startsWith(HELP.getCommand())){
            sendTextMessage(chatId, HELP_TEXT); return;
        }
        if(receivedMessage.startsWith(CONNECT_ACCOUNT.getCommand())){
            connectAccount(chatId); return;
        }
        if(receivedMessage.startsWith(CONFIRM_CONNECT_ACCOUNT.getCommand())){
            confirmConnection(chatId, receivedMessage); return;
        }
    }

    private void connectAccount(long chatId) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONNECT_ACCOUNT_TEXT);
        message.setReplyMarkup(Buttons.inlineConnectButton(chatId));
        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Привет, " + userName + "! Я бот напоминальщик");
        message.setReplyMarkup(Buttons.inlineMarkup());
        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void sendTextMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        message.setParseMode("HTML");
        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    @Override
    public void sendNotification(Notification notification) {
//        sendTextMessage(742774855, formatNotification(notification));
    }

    private String formatNotification(Notification notification){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMMM");
        return """
                Когда: %s
                У кого: %s
                Исполняется: %s лет
                Напоминаем за: %s дней
                """.formatted(dTF.format(notification.getBirthdayDate()),
                notification.getDetails(),
                LocalDate.now().getYear() - notification.getBirthdayDate().getYear(),
                notification.getPeriods().stream().map(p -> String.valueOf(p.getValue())).reduce((acc, v) -> acc + v + " ").orElse(""));
    }

    @Override
    public void sendConfirmation(long chatId, UUID userId, String username) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_CONNECTION_ACCOUNT_TEXT.formatted(username));
        message.setReplyMarkup(Buttons.inlineConnectConfirmationButton(userId));
        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    private void confirmConnection(long chatId, String receivedMessage){
        UUID userId = UUID.fromString(receivedMessage.split(" ")[1]);
        userService.addTelegramChat(chatId, userId);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Аккант подключен");
        try {
            execute(message);
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }
}
