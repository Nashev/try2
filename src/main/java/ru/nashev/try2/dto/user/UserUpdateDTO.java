package ru.nashev.try2.dto.user;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Входящий DTO для команды изменения пользователя
 * @author Nashev
 */
@Value
public class UserUpdateDTO {
    @Min(0) Long id;
    @Min(0) Long officeId;
    @Size(max = 255) @NotBlank String firstName;
    @Size(max = 255) String secondName;
    @Size(max = 255) String middleName;
    @Size(max = 255) @NotBlank String position;
    @Size(max = 255) String phone;
    @Size(max = 255) String docName;
    @Size(max = 255) String docNumber;
    @Size(max = 255) String docDate;
    @Size(max = 255) String citizenshipCode;
    Boolean isIdentified;
}