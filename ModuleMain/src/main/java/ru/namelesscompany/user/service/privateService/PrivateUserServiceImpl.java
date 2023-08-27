package ru.namelesscompany.user.service.privateService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.mapper.UserMapper;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivateUserServiceImpl implements PrivateUserService {

    private final UserRepository userRepository;

    @Override
    public UserDto get(long id) {
        return UserMapper.mapToUserDto(getUserIfExists(id));
    }

    @Override
    public UserDto add(NewUserDto newUserDto) {
        User user = UserMapper.mapToUser(newUserDto);
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(NewUserDto updateUser, long id) {
        User user = getUserIfExists(id);
        if (updateUser.getName() != null) {
            user.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null) {
            user.setEmail(updateUser.getEmail());
        }
        return UserMapper.mapToUserDto(user);
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
