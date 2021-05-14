package ru.nashev.try2.service.impl;

import ru.nashev.try2.service.UserError;

/**
 * Класс пользовательской ошибки про отсутствие записи.
 * Такая ошибка нужна для сервисов, предлагающих найти одну запись по id или коду
 * @author Nashev
 */
@UserError
public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String message) {
        super(message);
    }

}
