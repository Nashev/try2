package ru.nashev.try2.service;


import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;

public interface UserService extends GenericService<UserGetDTO, UserListDTO, UserFilter, UserAddDTO, UserUpdateDTO> {
}