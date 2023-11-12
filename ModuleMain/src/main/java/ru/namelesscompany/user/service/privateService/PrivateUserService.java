package ru.namelesscompany.user.service.privateService;

import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;

public interface PrivateUserService {

    UserDto get(long id);
    FullUserDto add(NewUserDto newUserDto);
    UserDto update(NewUserDto updateUser, long id);
    String delete(long id);
}
