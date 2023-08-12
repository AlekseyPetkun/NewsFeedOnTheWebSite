package com.example.newsFeedApp.exception;

/**
 * Ошибка валидации.
 */
public class ValidationException extends RuntimeException{

    private final String text;

    public ValidationException(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return "Сущность не прошла валидность! \n" + text;
    }
}
