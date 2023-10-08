package ru.namelesscompany.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.film.model.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByIdIn(List<Long> ids);
    List<Film> findAllByNameContainingIgnoreCase(String name);
}
