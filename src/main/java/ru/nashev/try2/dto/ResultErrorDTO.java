package ru.nashev.try2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * Исходящий DTO, через который, вместо результатов выполнения команды, передаются обнаруженые при выполнении команды ошибки
 * @author Nashev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultErrorDTO {
    String error;
}
