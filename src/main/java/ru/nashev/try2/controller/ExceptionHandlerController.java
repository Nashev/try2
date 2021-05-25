package ru.nashev.try2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.nashev.try2.dto.ResultErrorDTO;
import ru.nashev.try2.service.AppProperties;
import ru.nashev.try2.service.UserError;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Обработчик ошибок rest-сервиса
 * @author Nashev
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    /**
     * mapped from app.properties.author_email
     */
    @Autowired
    private AppProperties appProperties;

    /**
     * Обработка ошибок валидации аргументов у методов сервисов
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultErrorDTO onConstraintValidationException(ConstraintViolationException e) {
        final String violations = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath().toString() + " (" + violation.getMessage() + ")")
                .sorted()
                .collect(Collectors.joining("\n  "));
        return new ResultErrorDTO("Проблемы с аргументами:\n  " + violations);
    }

    /**
     * Обработка ошибок разбора аргументов у методов сервисов
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultErrorDTO onHttpMessageNotReadableException(MethodArgumentTypeMismatchException e) {
        return new ResultErrorDTO("Ошибка в запросе: " + e.getMessage());
    }

    /**
     * Обработка ошибок валидации полей JSON, присланных методу сервиса
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultErrorDTO onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final String violations = e.getBindingResult().getFieldErrors().stream()
                .map(violation -> violation.getField() + " (" + violation.getDefaultMessage() + ")")
                .sorted()
                .collect(Collectors.joining("\n  "));
        return new ResultErrorDTO("Ошибка в запросе, проблемы с полями:\n  " + violations);
    }

    /**
     * Обработка ошибок разбора аргументов у методов сервисов
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultErrorDTO onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResultErrorDTO("Ошибка в запросе, прислан некорректный JSON: " + e.getMessage());
    }

    /**
     * Обработчик остальных ошибок REST-сервиса.
     * Для пользовательских ошибок возвращает текст ошибки, все прочие ошибки считает системными,
     * и возвращает про них один и тот же текст с уникальным кодом. Саму ошибку с этим же кодом записывает в журнал.
     * Пользовательскими ошибками считает те, классы которых помечены аннотацией @UserError,
     * @return ResultErrorDTO: '{error: "error text"}'
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultErrorDTO unhandledException(Exception e) {
        if (e.getClass().isAnnotationPresent(UserError.class)) {
            return new ResultErrorDTO(e.getMessage());
        } else {
            String errorIdent = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
            log.error(errorIdent, e);
            return new ResultErrorDTO("Системная ошибка #" + errorIdent + ", пожалуйста обратитесь к разработчику системы по e-mail " + appProperties.getAuthorEmail());
        }
    }
}

