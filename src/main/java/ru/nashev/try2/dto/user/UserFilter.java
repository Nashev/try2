package ru.nashev.try2.dto.user;

import lombok.Data;

import java.util.Optional;

@Data
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class UserFilter {
    private Long officeId;
    private Optional<String> firstName = Optional.empty();
    private Optional<String> secondName = Optional.empty();
    private Optional<String> middleName = Optional.empty();
    private Optional<String> position = Optional.empty();
    private Optional<String> docCode = Optional.empty();
    private Optional<String> citizenshipCode = Optional.empty();
}