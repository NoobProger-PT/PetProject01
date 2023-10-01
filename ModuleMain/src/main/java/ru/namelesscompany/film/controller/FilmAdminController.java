package ru.namelesscompany.film.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.Marker;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;
import ru.namelesscompany.film.service.adminService.AdminFilmService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/films")
@Slf4j
@Validated
public class FilmAdminController {
    private final AdminFilmService service;

    @PostMapping
    public FullFilmDto add(@Validated({Marker.Create.class}) @RequestBody NewFilmDto newFilmDto) {
        log.info("Добавление нового фильма админом под названием: {}", newFilmDto.getName());
        return service.add(newFilmDto);
    }

    @PatchMapping("/{filmId}")
    public FullFilmDto update(@PathVariable @Positive Long filmId,
                              @Validated({Marker.Update.class}) @RequestBody NewFilmDto updatedFilm) {
        log.info("Админ редактирует данные фильма с ID: {}", filmId);
        return service.update(updatedFilm, filmId);
    }

    @GetMapping("/{filmId}")
    public FullFilmDto get(@PathVariable @Positive Long filmId) {
        log.info("Получение фильма по ID админом.");
        return service.getById(filmId);
    }

    @GetMapping("/all")
    public List<FullFilmDto> getAll() {
        log.info("Получение всех фильмов админом");
        return service.getAll();
    }

    @GetMapping("/several")
    public List<FullFilmDto> getByIds(@RequestParam(defaultValue = "") List<Long> ids) {
        log.info("Получение фильмов админом с ID: {}", ids);
        return service.getByIds(ids);
    }

    @GetMapping("/search")
    public List<FullFilmDto> getByName(@RequestParam String name) {
        log.info("Поиск фильма админом с названием: {}", name);
        return service.getByName(name);
    }

    @DeleteMapping("/{filmId}")
    public String delete(@PathVariable @Positive Long filmId) {
        log.info("Удаление фильма админом.");
        return service.delete(filmId);
    }
}
