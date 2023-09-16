package ru.namelesscompany.mpaa.service.publicService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.mpaa.MpaaNotFound;
import ru.namelesscompany.mpaa.dto.MpaaDto;
import ru.namelesscompany.mpaa.mapper.MpaaMapper;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.mpaa.repository.MpaaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicMpaaServiceImpl implements PublicMpaaService {

    private final MpaaRepository mpaaRepository;

    @Override
    public MpaaDto getById(long id) {
        return MpaaMapper.mapToMpaaDto(getMpaaIfExists(id));
    }

    @Override
    public List<MpaaDto> getAll() {
        return mpaaRepository.findAll().stream()
                .map(MpaaMapper::mapToMpaaDto)
                .collect(Collectors.toList());
    }

    private Mpaa getMpaaIfExists(long id) {
        return mpaaRepository.findById(id).orElseThrow(() -> new MpaaNotFound("Рейтинг Mpaa с id: " + id + " не найден"));
    }
}
