package ru.nashev.try2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

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

import java.util.List;

@Api(tags = "Office API")
@RequestMapping("/api/office")
@RestController
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeService userService;

    @ApiOperation("Find data with filter")
    @PostMapping("/list")
    public List<OfficeListDTO> list(@RequestBody OfficeFilter filter) {
        return userService.list(filter);
    }

    @ApiOperation("Find by Id")
    @GetMapping("/{id}")
    public OfficeGetDTO findById(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void save(@RequestBody OfficeAddDTO input) {
        userService.add(input);
    }

    @ApiOperation("Update one data")
    @PostMapping("/update")
    public void update(@RequestBody OfficeUpdateDTO input) {
        userService.update(input);
    }
}