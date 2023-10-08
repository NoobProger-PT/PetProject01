package ru.namelesscompany.film.service.privateService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.film.FilmNotFound;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.film.dto.FilmDto;
import ru.namelesscompany.film.mapper.FilmMapper;
import ru.namelesscompany.film.repository.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivateFilmServiceImpl implements PrivateFilmService {
    private final FilmRepository filmRepository;

    @Override
    public FilmDto getById(long id) {
        return FilmMapper.mapToFilmDto(getFilmIfExists(id));
    }

    @Override
    public List<FilmDto> getByName(String name) {
        return filmRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(FilmMapper::mapToFilmDto)
                .collect(Collectors.toList());
    }

    private Film getFilmIfExists(long id) {
        return filmRepository.findById(id).orElseThrow(() -> new FilmNotFound("Фильм не найден"));
    }
}
