package ru.nashev.try2.dto;

import lombok.Value;

/**
 * Исходящий DTO, через который, вместо результатов выполнения команды, передаются обнаруженые при выполнении команды ошибки
 * @author Nashev
 */
@Value
public class ResultErrorDTO {
    String error;
}
