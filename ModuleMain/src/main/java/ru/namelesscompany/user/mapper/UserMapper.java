package ru.namelesscompany.user.mapper;

import ru.namelesscompany.security.role.model.Role;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.dto.UserDto;
import ru.namelesscompany.user.dto.FullUserDto;
import ru.namelesscompany.user.dto.ShortUserDto;
import ru.namelesscompany.user.model.User;

import java.time.LocalDate;

public class UserMapper {
    public static User mapToUser(NewUserDto newUserDto) {
        User user = new User();
        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());
        user.setRegistrationDate(LocalDate.now());
        user.setPassword(newUserDto.getPassword());
        user.setOverlord(false);
        user.setPassword(newUserDto.getPassword());
        user.setRole(new Role(3L, "ROLE_USER"));
        return user;
    }

    public static FullUserDto mapToUserFullDto(User user) {
        FullUserDto fullUserDto = new FullUserDto();
        fullUserDto.setId(user.getId());
        fullUserDto.setName(user.getName());
        fullUserDto.setEmail(user.getEmail());
        fullUserDto.setRegistrationDate(user.getRegistrationDate());
        fullUserDto.setPassword(user.getPassword());
        fullUserDto.setOverlord(user.getOverlord());
        fullUserDto.setRole(user.getRole());
        return fullUserDto;
    }

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRegistrationDate(user.getRegistrationDate());
        return userDto;
    }

    public static ShortUserDto mapToUserShortDto(User user) {
        ShortUserDto shortUserDto = new ShortUserDto();
        shortUserDto.setName(user.getName());
        return shortUserDto;
    }
}
