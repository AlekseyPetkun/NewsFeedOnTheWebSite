package com.example.newsFeedApp.service;

/**
 * Сервис по работе с валидностью
 */
public interface ValidationService {

    /**
     * Валидация сущностей.
     *
     * @param object сущность.
     * @return валидная сущность.
     */
    boolean validate(Object object);
}
