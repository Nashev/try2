package ru.nashev.try2.dto.organization;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Входящий DTO для команды изменения организации
 * @author Nashev
 */
@Value
public class OrganizationUpdateDTO {
    @Min(0) Long id;
    @Size(max = 255) @NotBlank String name;
    @Size(max = 255) @NotBlank String fullName;
    @Size(max = 255) @NotBlank String inn;
    @Size(max = 255) @NotBlank String kpp;
    @Size(max = 4000) @NotBlank String address;
    String phone;
    Boolean isActive;
}