package com.example.newsFeedApp.exception;

/**
 * Ошибка поиска новости
 */
public class NotFindFeedException extends RuntimeException{

    private final long id;
    public NotFindFeedException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "новость с id: " + id + " не найдена!";
    }
}
