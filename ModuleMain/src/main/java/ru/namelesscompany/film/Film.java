/*package ru.namelesscompany.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.mpaa.model.Mpaa;

import javax.persistence.*;
import java.util.HashSet;
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
    private String releaseDate;
    @Column(name = "duration", nullable = false)
    private int duration;
    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Set<Genre> genres = new HashSet<>();
    @ManyToOne(optional = false)
    @JoinColumn(name = "mpaa_id", nullable = false)
    private Set<Mpaa> mpaa = new HashSet<>();
}
*/