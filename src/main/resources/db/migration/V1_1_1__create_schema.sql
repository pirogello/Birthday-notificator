create table IF NOT EXISTS notification
(
    id            serial primary key,
    details       varchar(1000),
    birthday_date date NOT NULL
);

create table IF NOT EXISTS periods
(
    notification_id integer,
    c_period        varchar(20),
    CONSTRAINT FK_period_notification FOREIGN KEY (notification_id)
        REFERENCES notification (id)
);