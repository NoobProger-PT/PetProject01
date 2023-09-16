package ru.namelesscompany.genre.service.adminService;

import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;

import java.util.List;

public interface AdminGenreService {
    FullGenreDto add(NewGenreDto newGenreDto);
    FullGenreDto getById(long id);
    List<FullGenreDto> getAll();
    FullGenreDto change(NewGenreDto newGenreDto, long id);
    String delete(long id);
}
