package ru.namelesscompany.mpaa.service.adminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.mpaa.MpaaNotFound;
import ru.namelesscompany.mpaa.dto.FullMpaaDto;
import ru.namelesscompany.mpaa.dto.NewMpaaDto;
import ru.namelesscompany.mpaa.mapper.MpaaMapper;
import ru.namelesscompany.mpaa.model.Mpaa;
import ru.namelesscompany.mpaa.repository.MpaaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminMpaaServiceImpl implements AdminMpaaService {

    private final MpaaRepository mpaaRepository;

    @Override
    public FullMpaaDto add(NewMpaaDto newMpaaDto) {
        Mpaa mpaa = mpaaRepository.save(MpaaMapper.mapToMpaa(newMpaaDto));
        return MpaaMapper.mapToFullMpaDto(mpaa);
    }

    @Override
    public FullMpaaDto getById(long id) {
        return MpaaMapper.mapToFullMpaDto(getMpaaIfExists(id));
    }

    @Override
    public List<FullMpaaDto> getAll() {
        return mpaaRepository.findAll().stream()
                .map(MpaaMapper::mapToFullMpaDto)
                .collect(Collectors.toList());
    }

    @Override
    public FullMpaaDto change(NewMpaaDto newMpaaDto, long id) {
        Mpaa mpaa = getMpaaIfExists(id);
        mpaa.setName(newMpaaDto.getName());
        return MpaaMapper.mapToFullMpaDto(mpaa);
    }

    @Override
    public String delete(long id) {
        getMpaaIfExists(id);
        mpaaRepository.deleteById(id);
        return "Рейтинг Mpaa удален.";
    }

    private Mpaa getMpaaIfExists(long id) {
        return mpaaRepository.findById(id).orElseThrow(() -> new MpaaNotFound("Рейтинг Mpaa с id: " + id + " не найден"));
    }
}
