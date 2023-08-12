package com.example.newsFeedApp.exception;

/**
 * Ошибка поиска списка
 */
public class NotFindListException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Список пустой!";
    }
}
