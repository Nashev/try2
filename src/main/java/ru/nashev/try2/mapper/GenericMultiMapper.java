package ru.nashev.try2.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Базовое объявление для генерации через mapstruct маппера списка сущностей на список DTO,
 * а так же маппера для получения одной DTO по сущности
 * и мапперов для получения или обновления сущностей по соответствующим DTO
 * @author Nashev
 */
public interface GenericMultiMapper<E, GET, LIST, ADD, UPDATE> extends GenericListMapper<E, LIST> {
    public GET get(E entity);
    public E add(ADD dto);
    public void update(@MappingTarget E entity, UPDATE dto);
}
