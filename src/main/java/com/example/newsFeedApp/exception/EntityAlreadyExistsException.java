package com.example.newsFeedApp.exception;

/**
 * Ошибка проверки наличия сущности в БД
 */
public class EntityAlreadyExistsException extends RuntimeException{

    private final String text;

    public EntityAlreadyExistsException(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return "Сущность: " + text + " уже существует!";
    }
}
