package ru.namelesscompany.film.service.adminService;

import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;

import java.util.List;

public interface AdminFilmService {
    FullFilmDto add(NewFilmDto newFilmDto);
    FullFilmDto update(NewFilmDto newFilmDto, long id);
    List<FullFilmDto> getAll();
    FullFilmDto getById(long id);
    List<FullFilmDto> getByIds(List<Long> ids);
    List<FullFilmDto> getByName(String name);
    String delete(long id);
}