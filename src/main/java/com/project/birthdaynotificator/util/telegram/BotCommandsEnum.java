package com.project.birthdaynotificator.util.telegram;

import lombok.Getter;

@Getter
public enum BotCommandsEnum {

    START("/start"),
    HELP("/help"),
    CONNECT_ACCOUNT("/connect"),
    CONFIRM_CONNECT_ACCOUNT("/confirm_connect");


    private final String command;

    BotCommandsEnum(String s) {
        this.command = s;
    }

}
