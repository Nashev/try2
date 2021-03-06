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
import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;
import ru.nashev.try2.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Обработчик рест-сервиса по адресу http:localhost:8888/api/user
 * @author Nashev
 */
@Api(tags = "User API")
@RequestMapping("/api/user")
@RestController
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    /**
     * Команда получения списка известных сервису пользователей, с фильтрацией по UserFilter
     */
    @ApiOperation("Find data with filter")
    @PostMapping("/list")
    public List<UserListDTO> list(@RequestBody @Valid UserFilter filter) {
        return service.list(filter);
    }

    /**
     * Команда получения информации по одному из известных сервису пользователей, по id
     */
    @ApiOperation("Find by Id")
    @GetMapping("/{id}")
    public UserGetDTO get(@PathVariable("id") @Min(0) Long id) {
        return service.get(id);
    }

    /**
     * Команда добавления нового пользователей
     */
    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void add(@RequestBody @Valid UserAddDTO input) {
        service.add(input);
    }

    /**
     * Команда редактирования уже известного сервису пользователя, по Id
     */
    @ApiOperation("Update one data")
    @PostMapping("/update")
    public void update(@RequestBody @Valid UserUpdateDTO input) {
        service.update(input);
    }
}