package ru.namelesscompany.user.service.publicService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.mapper.UserMapper;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicUserServiceImpl implements PublicUserService {

    private final UserRepository userRepository;

    @Override
    public ShortUserDto get(long id) {
        return UserMapper.mapToUserShortDto(getUserIfExists(id));
    }

    private User getUserIfExists(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("Пользователь не найден"));
    }
}
