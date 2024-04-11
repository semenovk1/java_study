# Настройка подключения к БД
В файле pom.xml в секции _`<properties>`_ _(~16 строка)_ настриавается подключение к БД:

    <db.Url>jdbc:oracle:thin:@95.161.178.222:45782/STUDYDB</db.Url> --Адресс сервера/БД
    <db.User>STUDY2</db.User> -- Имя пользователя/схемы
    <db.Password>123456</db.Password> -- Пароль
    <db.Schema>${db.User}</db.Schema> -- Изменить если имя схемы отлично от пользователя.
