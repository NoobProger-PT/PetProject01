package ru.namelesscompany.subAdmin.service.publicSubAdmin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.admin_subadmin.SubAdminNotFound;
import ru.namelesscompany.subAdmin.dto.SubAdminDto;
import ru.namelesscompany.subAdmin.mapper.SubAdminMapper;
import ru.namelesscompany.subAdmin.model.SubAdmin;
import ru.namelesscompany.subAdmin.repository.SubAdminRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicSubAdminServiceImpl implements PublicSubAdminService {
    private final SubAdminRepository subAdminRepository;
    @Override
    public SubAdminDto getById(long id) {
        return SubAdminMapper.mapToSubAdminDto(getSubAdminIfExists(id));
    }

    private SubAdmin getSubAdminIfExists(long id) {
        return subAdminRepository.findById(id).orElseThrow(() -> new SubAdminNotFound("Субадмин не найден"));
    }
}
