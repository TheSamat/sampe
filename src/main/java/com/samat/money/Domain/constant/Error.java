package com.samat.money.Domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {
    SERVLET_EXCEPTION(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST, "Запрос не получилось обработать"),
    IO_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, "Не удалось взаимодействовать с файлом"),

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED, "Неправильный логин или пароль"),
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, ErrorCode.FORBIDDEN, "Пользователь не авторизован"),

    GATEWAY_NOT_ASK(HttpStatus.BAD_GATEWAY, ErrorCode.BAD_GATEWAY, "Нет доступа к стороннему ресурсу"),
    GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, ErrorCode.TIMEOUT, "Сторонний ресурс не отвечает"),

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, "Сущность с этим id не найден"),
    ENTITY_DELETED(HttpStatus.GONE, ErrorCode.IS_DELETED, "Сущность с этим id был удален"),
    ENTITY_IS_REFERENCED(HttpStatus.CONFLICT, ErrorCode.IS_LINKED, "На Сущность еще ссылается другие Сущности"),

    ;

    HttpStatus status;
    ErrorCode code;
    String message;

}
