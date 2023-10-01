package ru.namelesscompany.film.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.namelesscompany.Marker;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class NewFilmDto {
    @NotBlank(groups = {Marker.Create.class})
    @Length(max = 60, groups = {Marker.Create.class, Marker.Update.class})
    private String name;
    @NotBlank(groups = {Marker.Create.class})
    @Length(max = 20, groups = {Marker.Create.class, Marker.Update.class})
    private String author;
    @NotBlank(groups = {Marker.Create.class})
    @Length(max = 20, groups = {Marker.Create.class, Marker.Update.class})
    private String country;
    @NotNull(groups = {Marker.Create.class})
    private LocalDate releaseDate;
    @NotNull(groups = {Marker.Create.class})
    @Positive(groups = {Marker.Create.class})
    private Integer duration;
    @NotNull(groups = {Marker.Create.class})
    private Set<Genre> genres;
    @NotNull(groups = {Marker.Create.class})
    private Mpaa mpaa;
}
