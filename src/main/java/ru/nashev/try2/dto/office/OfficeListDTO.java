package ru.nashev.try2.dto.office;

import lombok.Value;

@Value
public class OfficeListDTO {
    Long id;
    String name;
    Boolean isActive;
}