package com.example.newsFeedApp.exception;

/**
 * Ошибка поиска категории
 */
public class NotFindNewsCategoryException extends NotFoundException{

    private final String text;

    public NotFindNewsCategoryException(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return "Категория: " + text + " не существует!";
    }
}
