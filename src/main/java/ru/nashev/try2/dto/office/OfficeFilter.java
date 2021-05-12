package ru.nashev.try2.dto.office;

import lombok.Data;

import java.util.Optional;

@Data
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OfficeFilter {
    private Long orgId;
    private Optional<String> name = Optional.empty();
    private Optional<String> phone = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
}