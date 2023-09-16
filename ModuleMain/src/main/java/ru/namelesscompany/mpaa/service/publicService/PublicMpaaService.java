package ru.namelesscompany.mpaa.service.publicService;

import ru.namelesscompany.mpaa.dto.MpaaDto;

import java.util.List;

public interface PublicMpaaService {
    MpaaDto getById(long id);
    List<MpaaDto> getAll();
}
