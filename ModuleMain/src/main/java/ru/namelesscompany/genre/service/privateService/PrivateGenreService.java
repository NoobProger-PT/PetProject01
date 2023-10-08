package ru.namelesscompany.genre.service.privateService;

import ru.namelesscompany.genre.dto.GenreDto;

import java.util.List;

public interface PrivateGenreService {
    GenreDto getById(long id);
    List<GenreDto> getAll();
}
