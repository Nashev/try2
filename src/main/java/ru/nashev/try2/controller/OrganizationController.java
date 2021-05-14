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
import ru.nashev.try2.dto.organization.OrganizationAddDTO;
import ru.nashev.try2.dto.organization.OrganizationFilter;
import ru.nashev.try2.dto.organization.OrganizationGetDTO;
import ru.nashev.try2.dto.organization.OrganizationListDTO;
import ru.nashev.try2.dto.organization.OrganizationUpdateDTO;
import ru.nashev.try2.service.OrganizationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Обработчик рест-сервиса по адресу http:localhost:8888/api/organization
 * @author Nashev
 */
@Api(tags = "Organization API")
@RequestMapping("/api/organization")
@RestController
@Validated
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService service;

    /**
     * Команда получения списка известных сервису организаций, с фильтрацией по OrganizationFilter
     */
    @ApiOperation("Find data with filter")
    @PostMapping("/list")
    public List<OrganizationListDTO> list(@RequestBody @Valid OrganizationFilter filter) {
        return service.list(filter);
    }

    /**
     * Команда получения информации по одной из известных сервису организаций, по id
     */
    @ApiOperation("Find by Id")
    @GetMapping("/{id}")
    public OrganizationGetDTO get(@PathVariable("id") @Min(0) Long id) {
        return service.get(id);
    }

    /**
     * Команда добавления новой организации
     */
    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void add(@RequestBody @Valid OrganizationAddDTO input) {
        service.add(input);
    }

    /**
     * Команда редактирования уже известной сервису организации, по Id
     */
    @ApiOperation("Update one data")
    @PostMapping("/update")
    public void update(@RequestBody @Valid OrganizationUpdateDTO input) {
        service.update(input);
    }
}