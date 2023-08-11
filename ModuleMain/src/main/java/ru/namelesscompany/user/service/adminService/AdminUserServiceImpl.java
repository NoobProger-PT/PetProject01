package ru.namelesscompany.user.service.adminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.mapper.UserMapper;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;

    @Override
    public FullUserDto add(NewUserDto newUserDto) {
        User user = userRepository.save(UserMapper.mapToUser(newUserDto));
        return UserMapper.mapToUserFullDto(user);
    }

    @Override
    public FullUserDto getById(long id) {
        return UserMapper.mapToUserFullDto(getUserIfExists(id));
    }

    @Override
    public List<FullUserDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserFullDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FullUserDto> getByIds(List<Long> ids) {
        List<FullUserDto> result;
        if (ids.isEmpty()) {
            result = getAll();
        } else {
            result = userRepository.findAllByIdIn(ids).stream()
                    .map(UserMapper::mapToUserFullDto)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public FullUserDto update(NewUserDto newUserDto) {
        User user = getUserIfExists(newUserDto.getId());
        if (newUserDto.getName() != null) {
            user.setName(newUserDto.getName());
        }
        if (newUserDto.getEmail() != null) {
            user.setEmail(newUserDto.getEmail());
        }
        return UserMapper.mapToUserFullDto(user);
    }

    @Override
    public String delete(long id) {
        getUserIfExists(id);
        userRepository.deleteById(id);
        return "Пользователь удален";
    }

    private User getUserIfExists(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("Пользователь не найден"));
    }
}
