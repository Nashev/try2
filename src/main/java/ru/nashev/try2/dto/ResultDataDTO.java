package ru.nashev.try2.dto;

import lombok.Value;

/**
 * Исходящий DTO, в который заворачиваются все успешные результаты команд
 * @author Nashev
 */
@Value
public class ResultDataDTO {
    Object data;
}
