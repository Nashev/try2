package ru.nashev.try2.dto.office;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Optional;

/**
 * Входящий DTO фильтрации для команды получения списков офисов
 * @author Nashev
 */
@Data
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OfficeFilter {
    @Min(0) @NotNull private Long orgId;
    private Optional<@Size(max = 255) String> name = Optional.empty();
    private Optional<@Size(max = 255) String> phone = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
}