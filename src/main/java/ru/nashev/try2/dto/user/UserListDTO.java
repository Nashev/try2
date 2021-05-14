package ru.nashev.try2.dto.user;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации о списке пользователей
 * @author Nashev
 */
@Value
public class UserListDTO {
    Long id;
    String firstName;
    String secondName;
    String middleName;
    String position;
}