package ru.namelesscompany.film.service.privateService;

import ru.namelesscompany.film.dto.FilmDto;

import java.util.List;

public interface PrivateFilmService {
    FilmDto getById(long id);
    List<FilmDto> getByName(String name);
}
