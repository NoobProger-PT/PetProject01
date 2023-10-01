package ru.namelesscompany.film.service.adminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.film.FilmNotFound;
import ru.namelesscompany.film.model.Film;
import ru.namelesscompany.film.dto.FullFilmDto;
import ru.namelesscompany.film.dto.NewFilmDto;
import ru.namelesscompany.film.mapper.FilmMapper;
import ru.namelesscompany.film.repository.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminFilmServiceImpl implements AdminFilmService {
    private final FilmRepository filmRepository;

    @Override
    public FullFilmDto add(NewFilmDto newFilmDto) {
        Film film = filmRepository.save(FilmMapper.mapToFilm(newFilmDto));
        return FilmMapper.mapToFullFilmDto(film);
    }

    @Override
    public FullFilmDto update(NewFilmDto newFilmDto, long id) {
        Film film = getFilmIfExists(id);
        if (newFilmDto.getName() != null) {
            film.setName(newFilmDto.getName());
        }
        if (newFilmDto.getAuthor() != null) {
            film.setAuthor(newFilmDto.getAuthor());
        }
        if (newFilmDto.getCountry() != null) {
            film.setCountry(newFilmDto.getCountry());
        }
        if (newFilmDto.getDuration() != null) {
            film.setDuration(newFilmDto.getDuration());
        }
        if (newFilmDto.getReleaseDate() != null) {
            film.setReleaseDate(newFilmDto.getReleaseDate());
        }
        if (newFilmDto.getGenres() != null) {
            film.setGenres(newFilmDto.getGenres());
        }
        if (newFilmDto.getMpaa() != null) {
            film.setMpaa(newFilmDto.getMpaa());
        }
        return FilmMapper.mapToFullFilmDto(film);
    }

    @Override
    public List<FullFilmDto> getAll() {
        return filmRepository.findAll().stream()
                .map(FilmMapper::mapToFullFilmDto)
                .collect(Collectors.toList());
    }

    @Override
    public FullFilmDto getById(long id) {
        return FilmMapper.mapToFullFilmDto(getFilmIfExists(id));
    }

    @Override
    public List<FullFilmDto> getByIds(List<Long> ids) {
        List<FullFilmDto> result;
        if (ids.isEmpty()) {
            result = getAll();
        } else {
            result = filmRepository.findAllByIdIn(ids).stream()
                    .map(FilmMapper::mapToFullFilmDto)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public List<FullFilmDto> getByName(String name) {
        return filmRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(FilmMapper::mapToFullFilmDto)
                .collect(Collectors.toList());
    }

    @Override
    public String delete(long id) {
        getFilmIfExists(id);
        filmRepository.deleteById(id);
        return "Фильм удален";
    }

    private Film getFilmIfExists(long id) {
        return filmRepository.findById(id).orElseThrow(() -> new FilmNotFound("Фильм не найден"));
    }
}
