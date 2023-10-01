package ru.namelesscompany.film.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.service.publicService.PublicFilmService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public/films")
@Slf4j
@Validated
public class FilmPublicController {
    private final PublicFilmService service;

    @GetMapping("/{filmId}")
    public FilmDto get(@PathVariable @Positive Long filmId) {
        log.info("Получение фильма по ID гостем.");
        return service.getById(filmId);
    }

    @GetMapping("/search")
    public List<FilmDto> getByName(@RequestParam String name) {
        log.info("Поиск фильма гостем с названием: {}", name);
        return service.getByName(name);
    }
}
