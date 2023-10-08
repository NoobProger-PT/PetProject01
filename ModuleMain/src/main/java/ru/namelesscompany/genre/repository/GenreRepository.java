package ru.namelesscompany.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.genre.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
