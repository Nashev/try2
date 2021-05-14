package ru.nashev.try2.service;

import java.util.List;

/**
 * Базовый интерфейс сервисов для работы с неизменяемыми объектами
 * @author Nashev
 */
public interface GenericService<LIST> {
    List<LIST> list();
}