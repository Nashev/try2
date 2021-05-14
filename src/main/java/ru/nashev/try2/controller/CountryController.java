package ru.nashev.try2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nashev.try2.dto.CountryDTO;
import ru.nashev.try2.service.CountryService;

import java.util.List;

/**
 * Обработчик рест-сервиса по адресу http:localhost:8888/api/countries
 * @author Nashev
 */
@Api(tags = "Dictionaries API")
@RequestMapping("/api/countries")
@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    /**
     * Команда получения списка известных сервису стран
     */
    @ApiOperation("Get list of known Countries")
    @PostMapping
    public List<CountryDTO> list() {
        return countryService.list();
    }
}
