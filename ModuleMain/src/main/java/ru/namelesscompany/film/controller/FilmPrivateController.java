package ru.namelesscompany.film.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.service.privateService.PrivateFilmService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/private/films")
@Slf4j
@Validated
public class FilmPrivateController {
    private final PrivateFilmService service;

    @GetMapping("/{filmId}")
    public FilmDto get(@PathVariable @Positive Long filmId) {
        log.info("Получение фильма по ID пользователем.");
        return service.getById(filmId);
    }

    @GetMapping("/search")
    public List<FilmDto> getByName(@RequestParam String name) {
        log.info("Поиск фильма пользователем с названием: {}", name);
        return service.getByName(name);
    }
}
