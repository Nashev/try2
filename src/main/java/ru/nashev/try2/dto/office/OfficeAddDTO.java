package ru.nashev.try2.dto.office;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Входящий DTO для команды добавления офиса
 * @author Nashev
 */
@Value
public class OfficeAddDTO {
    @NotNull Long orgId;
    @Size(max = 255) String name;
    @Size(max = 4000) String address;
    @Size(max = 255) String phone;
    Boolean isActive;
}
