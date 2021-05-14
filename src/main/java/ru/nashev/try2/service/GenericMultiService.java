package ru.nashev.try2.service;

import java.util.List;

/**
 * Базовый интерфейс сервисов для работы с изменяемыми объектами
 * @author Nashev
 */
public interface GenericMultiService<GET, LIST, FILTER, ADD, UPDATE> {
    GET get(Long id);
    List<LIST> list(FILTER filter);
    void add(ADD dto);
    void update(UPDATE dto);
}