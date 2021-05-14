package ru.nashev.try2.dto.office;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации об одном офисе
 * @author Nashev
 */
@Value
public class OfficeGetDTO {
    Long id;
    String name;
    String address;
    String phone;
    Boolean isActive;
}