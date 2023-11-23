package com.samat.money.Application.exceprion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomError {
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Сущность с этим id не найден", "Не найдено"),
    ENTITY_DELETED(HttpStatus.GONE, "Сущность с этим id был удален", "Удалено"), //Админ будет видеть
    ENTITY_IS_REFERENCED(HttpStatus.CONFLICT, "На Сущность еще ссылается другие Сущности", "Имеются зависимости"),

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "Неправильный логин или пароль", "Не удалось авторизоваться"),
    NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "Пользователь не авторизован", "Не авторизован"),

    GATEWAY_NOT_ASK(HttpStatus.BAD_GATEWAY, "Нет доступа к стороннему ресурсу", "Нет ответа"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "Сторонний ресурс не отвечает", "Превышен лимит ответа"),

    IO_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Не удалось взаимодействовать с файлом", "Ошибка файла"),
    SERVLET_EXCEPTION(HttpStatus.BAD_REQUEST, "Запрос не получилось обработать", "Необрабатываемый запрос");


    HttpStatus status;
    String message;
    String title;

}
