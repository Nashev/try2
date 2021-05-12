package ru.nashev.try2.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMultiMapper<E, GET, LIST, ADD, UPDATE> {
    public GET get(E entity);
    public List<LIST> list(List<E> entities);
    public E add(ADD dto);
    public void update(@MappingTarget E entity, UPDATE dto);
}
