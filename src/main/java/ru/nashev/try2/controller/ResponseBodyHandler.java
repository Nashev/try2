package ru.nashev.try2.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.nashev.try2.dto.ResultDataDTO;
import ru.nashev.try2.dto.ResultErrorDTO;
import ru.nashev.try2.dto.ResultSuccessDTO;

/**
 * Обработчик рест-сервисов, заворачивающий результаты их работы в ResultDataDTO, если это не ошибка и не чужой DTO
 * @author Nashev
 */
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * Объявляет, что аспект должен применяться ко всем запросам
     * @return true
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Заворачивает все ответы в ResultDataDTO, за исключением ошибок. Если ответ void,
     * то в ResultDataDTO заворачивает экземпляр ResultSuccessDTO
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse response) {
        if (body == null) {
            return new ResultDataDTO(new ResultSuccessDTO());
        } else if (!body.getClass().getName().startsWith("ru.nashev.try2.dto")) { // skip swagger classes etc
            return body;
        } else if (body instanceof ResultErrorDTO) {
            return body;
        } else {
            return new ResultDataDTO(body);
        }
    }
}
