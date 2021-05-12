package ru.nashev.try2.service;

import ru.nashev.try2.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> list();
}