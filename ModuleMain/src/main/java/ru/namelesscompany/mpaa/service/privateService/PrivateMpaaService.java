package ru.namelesscompany.mpaa.service.privateService;

import ru.namelesscompany.mpaa.dto.MpaaDto;

import java.util.List;

public interface PrivateMpaaService {
    MpaaDto getById(long id);
    List<MpaaDto> getAll();
}
