package ru.namelesscompany.user.service.publicService;

import ru.namelesscompany.user.dto.ShortUserDto;

public interface PublicUserService {

    ShortUserDto get(long id);
}
