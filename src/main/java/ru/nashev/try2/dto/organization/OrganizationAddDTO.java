package ru.nashev.try2.dto.organization;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Входящий DTO для команды добавления организации
 * @author Nashev
 */
@Value
public class OrganizationAddDTO {
    @Size(max = 255) @NotBlank String name;
    @Size(max = 255) @NotBlank String fullName;
    @Size(max = 255) @NotBlank String inn;
    @Size(max = 255) @NotBlank String kpp;
    @Size(max = 4000) @NotBlank String address;
    @Size(max = 255) String phone;
    Boolean isActive;
}
