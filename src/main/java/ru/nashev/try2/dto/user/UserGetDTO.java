package ru.nashev.try2.dto.user;

import lombok.Value;

@Value
public class UserGetDTO {
    String id;
    String firstName;
    String secondName;
    String middleName;
    String position;
    String phone;
    String docName;
    String docNumber;
    String docDate;
    String citizenshipName;
    String citizenshipCode;
    String isIdentified;
}