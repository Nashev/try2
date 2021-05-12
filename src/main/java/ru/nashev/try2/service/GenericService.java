package ru.nashev.try2.service;

import java.util.List;

public interface GenericService<GET, LIST, FILTER, ADD, UPDATE> {
    public GET get(Long id);
    public List<LIST> list(FILTER filter);
    public void add(ADD dto);
    public void update(UPDATE dto);
}