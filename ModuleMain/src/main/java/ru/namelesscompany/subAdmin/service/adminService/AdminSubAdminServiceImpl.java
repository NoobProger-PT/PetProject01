package ru.namelesscompany.subAdmin.service.adminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;
import ru.namelesscompany.subAdmin.mapper.SubAdminMapper;
import ru.namelesscompany.subAdmin.model.SubAdmin;
import ru.namelesscompany.subAdmin.repository.SubAdminRepository;
import ru.namelesscompany.exceptions.admin_subadmin.SubAdminNotFound;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminSubAdminServiceImpl implements AdminSubAdminService {

    private final SubAdminRepository subAdminRepository;

    @Override
    public FullSubAdminDto add(NewSubAdminDto newSubAdminDto) {
        SubAdmin subAdmin = subAdminRepository.save(SubAdminMapper.mapToSubAdmin(newSubAdminDto));
        return SubAdminMapper.mapToFullSubAdminDto(subAdmin);
    }

    @Override
    public FullSubAdminDto getById(long id) {
        return SubAdminMapper.mapToFullSubAdminDto(getSubAdminIfExists(id));
    }

    @Override
    public List<FullSubAdminDto> getAll() {
        return subAdminRepository.findAll().stream()
                .map(SubAdminMapper::mapToFullSubAdminDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FullSubAdminDto> getByIds(List<Long> ids) {
        List<FullSubAdminDto> result;
        if (ids.isEmpty()) {
            result = getAll();
        } else {
            result = subAdminRepository.findAllByIdIn(ids).stream()
                    .map(SubAdminMapper::mapToFullSubAdminDto)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public FullSubAdminDto update(NewSubAdminDto newSubAdminDto, long id) {
        SubAdmin subAdmin = getSubAdminIfExists(id);
        if (newSubAdminDto.getName() != null) {
            subAdmin.setName(newSubAdminDto.getName());
        }
        if (newSubAdminDto.getEmail() != null) {
            subAdmin.setEmail(newSubAdminDto.getEmail());
        }
        return SubAdminMapper.mapToFullSubAdminDto(subAdmin);
    }

    @Override
    public String delete(long id) {
        getSubAdminIfExists(id);
        subAdminRepository.deleteById(id);
        return "Субадмин удален";
    }

    private SubAdmin getSubAdminIfExists(long id) {
        return subAdminRepository.findById(id).orElseThrow(() -> new SubAdminNotFound("Субадмин не найден"));
    }
}
