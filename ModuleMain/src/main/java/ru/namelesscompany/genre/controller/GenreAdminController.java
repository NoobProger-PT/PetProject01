package ru.namelesscompany.genre.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.service.adminService.AdminGenreService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/genre")
@Slf4j
@Validated
public class GenreAdminController {

    private final AdminGenreService service;

    @PostMapping
    public FullGenreDto add(@RequestBody @Valid NewGenreDto newGenreDto) {
        log.info("Добавление нового жанра админом с названием: {}", newGenreDto.getName());
        return service.add(newGenreDto);
    }

    @GetMapping("/{genreId}")
    public FullGenreDto get(@PathVariable @Positive long genreId) {
        log.info("Получение админом жанра с id: {}", genreId);
        return service.getById(genreId);
    }

    @GetMapping("/all")
    public List<FullGenreDto> getAll() {
        log.info("Получение админом всех жанров");
        return service.getAll();
    }

    @PatchMapping("/{genreId}")
    public FullGenreDto change(@PathVariable @Positive long genreId,
                              @RequestBody NewGenreDto newGenreDto) {
        log.info("Изменение админом жанра с id: {}", genreId);
        return service.change(newGenreDto, genreId);
    }

    @DeleteMapping("/{genreId}")
    public String delete(@PathVariable @Positive long genreId) {
        log.info("Уданение админом жанра с id: {}", genreId);
        return service.delete(genreId);
    }
}
