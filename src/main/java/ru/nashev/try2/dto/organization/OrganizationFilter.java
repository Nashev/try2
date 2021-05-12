package ru.nashev.try2.dto.organization;

import lombok.Data;

import java.util.Optional;

@Data
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OrganizationFilter {
    private String name;
    private Optional<String> inn = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
}