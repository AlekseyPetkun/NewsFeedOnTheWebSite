package com.example.newsFeedApp.exception;

/**
 * Ошибка поиска списка
 */
public class NotFindListException extends NotFoundException{

    @Override
    public String getMessage() {
        return "Список пустой!";
    }
}
