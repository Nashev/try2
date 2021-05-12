package ru.nashev.try2.dto.user;

import lombok.Value;

@Value
public class UserListDTO {
    Long id;
    String firstName;
    String secondName;
    String middleName;
    String position;
}