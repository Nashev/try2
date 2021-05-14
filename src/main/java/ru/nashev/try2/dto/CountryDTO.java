package ru.nashev.try2.dto;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации о списке стран
 * @author Nashev
 */
@Value
public class CountryDTO {
    private String code;
    private String name;
}
