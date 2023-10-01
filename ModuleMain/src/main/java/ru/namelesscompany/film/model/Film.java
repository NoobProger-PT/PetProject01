package ru.namelesscompany.film.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "country")
    private String country;
    @Column(name = "release_date")
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @ManyToMany
    @JoinColumn(name = "genre_id", nullable = false)
    private Set<Genre> genres;
    @ManyToOne
    @JoinColumn(name = "mpaa_id", nullable = false)
    private Mpaa mpaa;
}