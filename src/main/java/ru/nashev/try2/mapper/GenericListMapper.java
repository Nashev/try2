package ru.nashev.try2.mapper;

import java.util.List;

/**
 * Базовое объявление для генерации через mapstruct мапперов списка сущностей на список DTO
 * @author Nashev
 */
public interface GenericListMapper<E, D> {
    List<D> list(List<E> entityList);
}