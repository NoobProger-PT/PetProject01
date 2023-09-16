package ru.namelesscompany.genre.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.service.publicService.PublicGenreService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public/genre")
@Slf4j
@Validated
public class GenrePublicController {

    private final PublicGenreService service;

    @GetMapping("/{genreId}")
    public GenreDto get(@PathVariable @Positive long genreId) {
        log.info("Получение админом жанра с id: {}", genreId);
        return service.getById(genreId);
    }

    @GetMapping("/all")
    public List<GenreDto> getAll() {
        log.info("Получение админом всех жанров");
        return service.getAll();
    }
}
