package ru.nashev.try2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeFilter;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;
import ru.nashev.try2.service.OfficeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Обработчик рест-сервиса по адресу http:localhost:8888/api/office
 * @author Nashev
 */
@Api(tags = "Office API")
@RequestMapping("/api/office")
@RestController
@Validated
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService service;

    /**
     * Команда получения списка известных сервису офисов, с фильтрацией по OfficeFilter
     */
    @ApiOperation("Find data with filter")
    @PostMapping("/list")
    public List<OfficeListDTO> list(@RequestBody @Valid OfficeFilter filter) {
        return service.list(filter);
    }

    /**
     * Команда получения информации по одному из известных сервису офисов, по id
     */
    @ApiOperation("Find by Id")
    @GetMapping("/{id}")
    public OfficeGetDTO get(@PathVariable("id") @Min(0) Long id) {
        return service.get(id);
    }

    /**
     * Команда добавления нового офиса
     */
    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void add(@RequestBody @Valid OfficeAddDTO input) {
        service.add(input);
    }

    /**
     * Команда редактирования уже известного сервису офиса, по Id
     */
    @ApiOperation("Update one data")
    @PostMapping("/update")
    public void update(@RequestBody @Valid OfficeUpdateDTO input) {
        service.update(input);
    }
}