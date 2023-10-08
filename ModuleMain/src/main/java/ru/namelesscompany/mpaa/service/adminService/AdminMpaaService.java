package ru.namelesscompany.mpaa.service.adminService;

import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;

import java.util.List;

public interface AdminMpaaService {
    FullMpaaDto add(NewMpaaDto newMpaaDto);
    FullMpaaDto getById(long id);
    List<FullMpaaDto> getAll();
    FullMpaaDto change(NewMpaaDto newMpaaDto, long id);
    String delete(long id);
}
