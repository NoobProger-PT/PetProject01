package ru.namelesscompany.film.service.publicService;

import ru.namelesscompany.film.dto.FilmDto;

import java.util.List;

public interface PublicFilmService {
    FilmDto getById(long id);
    List<FilmDto> getByName(String name);
}
