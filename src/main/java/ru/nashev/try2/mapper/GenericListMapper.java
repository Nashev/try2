package ru.nashev.try2.mapper;

import java.util.List;

public interface GenericListMapper<E, D> {
    List<D> list(List<E> entityList);
}