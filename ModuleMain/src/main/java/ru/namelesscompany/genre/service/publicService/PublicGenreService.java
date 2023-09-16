package ru.namelesscompany.genre.service.publicService;

import ru.namelesscompany.genre.dto.GenreDto;

import java.util.List;

public interface PublicGenreService {
    GenreDto getById(long id);
    List<GenreDto> getAll();
}
