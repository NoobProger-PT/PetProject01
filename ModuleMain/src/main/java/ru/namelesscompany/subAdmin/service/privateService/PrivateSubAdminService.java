package ru.namelesscompany.subAdmin.service.privateService;

import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;

public interface PrivateSubAdminService {
    FullSubAdminDto getById(long id);
    FullSubAdminDto update(NewSubAdminDto newSubAdminDto, long id);

}
