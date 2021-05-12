package ru.nashev.try2.dto.organization;

import lombok.Value;

@Value
public class OrganizationAddDTO {
    String name;
    String fullName;
    String inn;
    String kpp;
    String address;
    String phone;
    Boolean isActive;
}