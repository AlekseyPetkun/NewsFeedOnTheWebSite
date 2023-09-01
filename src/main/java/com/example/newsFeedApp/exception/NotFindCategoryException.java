package com.example.newsFeedApp.exception;

/**
 * Ошибка поиска категории
 */
public class NotFindCategoryException extends NotFoundException{

    private final long id;

    public NotFindCategoryException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Категория с id: " + id + " не найдена!";
    }
}
