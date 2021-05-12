package ru.nashev.try2.dto.office;

import lombok.Value;

@Value
public class OfficeUpdateDTO {
    Long id;
    String name;
    String address;
    String phone;
    Boolean isActive;
}