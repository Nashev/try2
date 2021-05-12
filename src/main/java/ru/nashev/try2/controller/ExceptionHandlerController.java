package ru.nashev.try2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nashev.try2.dto.ResultErrorDTO;
import ru.nashev.try2.service.UserError;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ResultErrorDTO unhandledException(Exception e) {
        if (e.getClass().isAnnotationPresent(UserError.class)) {
            return new ResultErrorDTO(e.getMessage());
        } else {
            String errorIdent = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
            log.error(errorIdent, e);
            return new ResultErrorDTO("System error #" + errorIdent + ", please report to admin");
        }
    }
}

