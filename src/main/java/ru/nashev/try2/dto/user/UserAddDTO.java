package ru.nashev.try2.dto.user;

import lombok.Value;

@Value
public class UserAddDTO {
    Long officeId;
    String firstName;
    String secondName;
    String middleName;
    String position;
    String phone;
    String docCode;
    String docName;
    String docNumber;
    String docDate;
    String citizenshipCode;
    String isIdentified;
}