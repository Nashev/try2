package ru.nashev.try2.dto.organization;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Optional;

/**
 * Входящий DTO фильтрации для команды получения списков организаций
 * @author Nashev
 */
@Data
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OrganizationFilter {
    @Size(max = 255) @NotNull private String name;
    private Optional<@Size(max = 255) String> inn = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
}