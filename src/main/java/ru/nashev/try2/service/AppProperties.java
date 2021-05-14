package ru.nashev.try2.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Сервис получения настроек из файла настроек
 * @author Nashev
 */
@Data
@ConfigurationProperties(prefix="app.properties")
@Validated
@Service
public class AppProperties {
    /**
     * mapped from app.properties.author_email
     */
    @NotEmpty @Email
    String authorEmail;
}
