package ru.nashev.try2.dto.organization;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации о списке организаций
 * @author Nashev
 */
@Value
public class OrganizationListDTO {
    Long id;
    String name;
    Boolean isActive;
}