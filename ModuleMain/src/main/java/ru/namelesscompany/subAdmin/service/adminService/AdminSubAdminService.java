package ru.namelesscompany.subAdmin.service.adminService;

import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;

import java.util.List;

public interface AdminSubAdminService {
    FullSubAdminDto add(NewSubAdminDto newSubAdminDto);
    FullSubAdminDto getById(long id);
    List<FullSubAdminDto> getAll();
    List<FullSubAdminDto> getByIds(List<Long> ids);
    FullSubAdminDto update(NewSubAdminDto newSubAdminDto, long id);
    String delete(long id);
}
