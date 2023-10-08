package ru.namelesscompany.mpaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.mpaa.model.Mpaa;

public interface MpaaRepository extends JpaRepository<Mpaa, Long> {
}
