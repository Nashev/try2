package ru.nashev.try2.dto.organization;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации об одной организации
 * @author Nashev
 */
@Value
public class OrganizationGetDTO {
    Long id;
    String name;
    String fullName;
    String inn;
    String kpp;
    String address;
    String phone;
    Boolean isActive;
}