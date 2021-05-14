package ru.nashev.try2.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Анотация для помечания классов ошибок, текст которых должен будет отображаться пользователю
 * Остальные ошибки (за рядом исключений) считаются системными, и пишутся в лог с пометкой для облегчения поиска,
 * а пользователю предлагается с такой пометкой обратиться к автору приложения
 * @author Nashev
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserError {}
