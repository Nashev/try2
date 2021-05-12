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
import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;
import ru.nashev.try2.service.UserService;

import java.util.List;

@Api(tags = "User API")
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("Find data with filter")
    @PostMapping("/list")
    public List<UserListDTO> list(@RequestBody UserFilter filter) {
        return userService.list(filter);
    }

    @ApiOperation("Find by Id")
    @GetMapping("/{id}")
    public UserGetDTO findById(@PathVariable("id") Long id) {
        return userService.get(id);
    }

    @ApiOperation("Add new data")
    @PostMapping("/save")
    public void save(@RequestBody UserAddDTO input) {
        userService.add(input);
    }

    @ApiOperation("Update one data")
    @PostMapping("/update")
    public void update(@RequestBody UserUpdateDTO input) {
        userService.update(input);
    }
}