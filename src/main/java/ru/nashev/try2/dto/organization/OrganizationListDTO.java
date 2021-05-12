package ru.nashev.try2.dto.organization;

import lombok.Value;

@Value
public class OrganizationListDTO {
    Long id;
    String name;
    Boolean isActive;
}