package ru.namelesscompany.genre.service.adminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.genre.GenreNotFound;
import ru.namelesscompany.genre.dto.FullGenreDto;
import ru.namelesscompany.genre.dto.NewGenreDto;
import ru.namelesscompany.genre.mapper.GenreMapper;
import ru.namelesscompany.genre.model.Genre;
import ru.namelesscompany.genre.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminGenreServiceImpl implements AdminGenreService {

    private final GenreRepository genreRepository;

    @Override
    public FullGenreDto add(NewGenreDto newGenreDto) {
        Genre genre = genreRepository.save(GenreMapper.mapToGenre(newGenreDto));
        return GenreMapper.mapToFullGenreDto(genre);
    }

    @Override
    public FullGenreDto getById(long id) {
        return GenreMapper.mapToFullGenreDto(getGenreIfExists(id));
    }

    @Override
    public List<FullGenreDto> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::mapToFullGenreDto)
                .collect(Collectors.toList());
    }

    @Override
    public FullGenreDto change(NewGenreDto newGenreDto, long id) {
        Genre genre = getGenreIfExists(id);
        genre.setName(newGenreDto.getName());
        return GenreMapper.mapToFullGenreDto(genre);
    }

    @Override
    public String delete(long id) {
        getGenreIfExists(id);
        genreRepository.deleteById(id);
        return "Жанр удален.";
    }

    private Genre getGenreIfExists(long id) {
        return genreRepository.findById(id).orElseThrow(() -> new GenreNotFound("Жанр с id: " + id + " не найден"));
    }
}
