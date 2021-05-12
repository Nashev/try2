package ru.nashev.try2.dto.organization;

import lombok.Value;

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