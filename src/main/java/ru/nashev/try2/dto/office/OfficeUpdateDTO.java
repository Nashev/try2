package ru.nashev.try2.dto.office;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Входящий DTO для команды изменения офиса
 * @author Nashev
 */
@Value
public class OfficeUpdateDTO {
    @Min(0) Long id;
    @Size(max = 255) @NotBlank String name;
    @Size(max = 4000) @NotBlank String address;
    @Size(max = 255) String phone;
    Boolean isActive;
}