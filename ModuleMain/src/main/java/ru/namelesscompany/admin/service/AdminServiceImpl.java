package ru.namelesscompany.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.admin.dto.AdminDto;
import ru.namelesscompany.admin.dto.FullAdminDto;
import ru.namelesscompany.admin.dto.NewAdminDto;
import ru.namelesscompany.admin.mapper.AdminMapper;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.admin.repository.AdminRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public FullAdminDto getById(long id) {
        return AdminMapper.mapToFullAdminDto(adminRepository.findById(id).get());
    }

    @Override
    public FullAdminDto update(NewAdminDto newAdminDto, long id) {
        Admin admin = adminRepository.findById(id).get();
        if (newAdminDto.getName() != null) {
            admin.setName(newAdminDto.getName());
        }
        if (newAdminDto.getEmail() != null) {
            admin.setEmail(newAdminDto.getEmail());
        }
        if (newAdminDto.getPassword() != null) {
            admin.setPassword(newAdminDto.getPassword());
        }
        return AdminMapper.mapToFullAdminDto(admin);
    }

    @Override
    public AdminDto getAdminForUsers(long id) {
        return AdminMapper.mapToAdminDto(adminRepository.findById(id).get());
    }
}
