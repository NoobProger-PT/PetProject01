package ru.namelesscompany.user.service.privateService;

import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;

public interface PrivateUserService {

    UserDto get(long id);
    UserDto add(NewUserDto newUserDto);
    UserDto update(NewUserDto updateUser);
    String delete(long id);
}
