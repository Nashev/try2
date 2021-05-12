package ru.nashev.try2.dto.office;

import lombok.Value;

@Value
public class OfficeAddDTO {
    Long orgId;
    String name;
    String address;
    String phone;
    Boolean isActive;
}