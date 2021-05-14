package ru.nashev.try2.dto.office;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации о списке офисов
 * @author Nashev
 */
@Value
public class OfficeListDTO {
    Long id;
    String name;
    Boolean isActive;
}