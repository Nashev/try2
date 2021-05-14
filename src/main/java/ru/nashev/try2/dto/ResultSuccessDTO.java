package ru.nashev.try2.dto;

import lombok.Value;

/**
 * Исходящий DTO, который передаётся в качестве результата выполнения команды, если она не возвращает своих результатов
 * и не обнаруживает ошибку
 * @author Nashev
 */
@Value
public class ResultSuccessDTO {
    String result = "success";
}
