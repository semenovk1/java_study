# Настройка подключения к БД
В файле pom.xml в секции _`<properties>`_ _(~16 строка)_ настриавается подключение к БД:

    <db.Url>jdbc:oracle:thin:@HOST:PORT/DB_NAME</db.Url> --Адресс сервера/БД
    <db.User>USER</db.User> -- Имя пользователя/схемы
    <db.Password>PASSWORD</db.Password> -- Пароль
    <db.Schema>${db.User}</db.Schema> -- Изменить если имя схемы отлично от пользователя.
