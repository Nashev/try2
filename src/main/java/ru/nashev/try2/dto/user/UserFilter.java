package ru.nashev.try2.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Optional;

/**
 * Входящий DTO фильтрации для команды получения списков пользователей
 * @author Nashev
 */
@Data
@AllArgsConstructor
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class UserFilter {
    @Min(0) @NotNull private Long officeId;
    private Optional<@Size(max = 255) String> firstName = Optional.empty();
    private Optional<@Size(max = 255) String> secondName = Optional.empty();
    private Optional<@Size(max = 255) String> middleName = Optional.empty();
    private Optional<@Size(max = 255) String> position = Optional.empty();
    private Optional<@Size(max = 255) String> docCode = Optional.empty();
    private Optional<@Size(max = 255) String> citizenshipCode = Optional.empty();
}