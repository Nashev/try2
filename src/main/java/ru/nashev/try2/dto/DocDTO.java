package ru.nashev.try2.dto;

import lombok.Value;

/**
 * Исходящий DTO для команды получения информации о списке типов документов
 * @author Nashev
 */
@Value
public class DocDTO {
    private String code;
    private String name;
}
