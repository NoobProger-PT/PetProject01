package ru.namelesscompany.genre.service.privateService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.genre.GenreNotFound;
import ru.namelesscompany.genre.dto.GenreDto;
import ru.namelesscompany.genre.mapper.GenreMapper;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivateGenreServiceImpl implements PrivateGenreService{

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getById(long id) {
        return GenreMapper.mapToGenreDto(getGenreIfExists(id));
    }

    @Override
    public List<GenreDto> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::mapToGenreDto)
                .collect(Collectors.toList());
    }

    private Genre getGenreIfExists(long id) {
        return genreRepository.findById(id).orElseThrow(() -> new GenreNotFound("Жанр с id: " + id + " не найден"));
    }
}
