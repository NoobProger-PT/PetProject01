package ru.namelesscompany.film.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FullFilmDto {
    private Long id;
    private String name;
    private String author;
    private String country;
    private LocalDate releaseDate;
    private Integer duration;
    private Set<Genre> genres;
    private Mpaa mpaa;
}
