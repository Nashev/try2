package ru.nashev.try2.service;

import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;

/**
 * Интерфейс сервиса для работы с пользователями
 * @author Nashev
 */
public interface UserService extends GenericMultiService<UserGetDTO, UserListDTO, UserFilter, UserAddDTO, UserUpdateDTO> {
}