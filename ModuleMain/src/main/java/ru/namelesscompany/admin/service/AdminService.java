package ru.namelesscompany.admin.service;

import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;

public interface AdminService {
    FullAdminDto getById(long id);
    FullAdminDto update(NewAdminDto newAdminDto, long id);
    AdminDto getAdminForUsers(long id);
}
