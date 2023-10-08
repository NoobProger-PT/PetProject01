package ru.namelesscompany.user.service.adminService;

import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;

import java.util.List;

public interface AdminUserService {

    FullUserDto add(NewUserDto newUserDto);
    FullUserDto getById(long id);
    List<FullUserDto> getAll();
    List<FullUserDto> getByIds(List<Long> ids);
    FullUserDto update(NewUserDto newUserDto, long id);
    String delete(long id);
}
