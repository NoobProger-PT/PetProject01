package ru.namelesscompany.subAdmin.service.privateService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.subAdmin.dto.FullSubAdminDto;
import ru.namelesscompany.subAdmin.dto.NewSubAdminDto;
import ru.namelesscompany.subAdmin.dto.SubAdminDto;
import ru.namelesscompany.subAdmin.mapper.SubAdminMapper;
import ru.namelesscompany.subAdmin.model.SubAdmin;
import ru.namelesscompany.subAdmin.repository.SubAdminRepository;
import ru.namelesscompany.exceptions.admin_subadmin.SubAdminNotFound;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivateSubAdminServiceImpl implements PrivateSubAdminService {

    private final SubAdminRepository subAdminRepository;

    @Override
    public FullSubAdminDto getById(long id) {
        return SubAdminMapper.mapToFullSubAdminDto(getSubAdminIfExists(id));
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
        if (newSubAdminDto.getPassword() != null) {
            subAdmin.setPassword(newSubAdminDto.getPassword());
        }
        return SubAdminMapper.mapToFullSubAdminDto(subAdmin);
    }

    private SubAdmin getSubAdminIfExists(long id) {
        return subAdminRepository.findById(id).orElseThrow(() -> new SubAdminNotFound("Субадмин не найден"));
    }
}
