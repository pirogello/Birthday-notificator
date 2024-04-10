
create table IF NOT EXISTS t_user
(
    id              bigserial primary key,
    password        varchar(255),
    username        varchar(255) unique
);

create table IF NOT EXISTS user_telegram_chat_id
(
    chat_id         bigint,
    user_id         bigint not null,
    CONSTRAINT FK_user_telegram_chat_id FOREIGN KEY (user_id)
        REFERENCES t_user (id)
);

create table IF NOT EXISTS notification
(
    id            serial primary key,
    details       varchar(1000),
    user_id       bigint not null,
    birthday_date date not null,
    CONSTRAINT FK_user_notification FOREIGN KEY (user_id)
        REFERENCES t_user (id)
);

create table IF NOT EXISTS periods
(
    notification_id integer,
    c_period        varchar(20),
    CONSTRAINT FK_period_notification FOREIGN KEY (notification_id)
        REFERENCES notification (id)
);