package ru.namelesscompany.users.service.privat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;
import ru.namelesscompany.user.service.privateService.PrivateUserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PrivateUserServiceTest {
    private PrivateUserServiceImpl userService;
    private UserRepository userRepository;

    private final User user = new User();

    @BeforeEach
    public void seUp() {
        userRepository = mock(UserRepository.class);
        userService = new PrivateUserServiceImpl(userRepository);
        when(userRepository.save(any())).then(invocation -> invocation.getArgument(0));

        user.setId(1L);
        user.setName("Name1");
        user.setEmail("mail@mail.com");
    }

    @Test
    public void shouldAddNewUser() {
        when(userRepository.save(any())).thenReturn(user);
        var result = userService.add(new NewUserDto());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    public void shouldGetById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        var result = userService.get(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldNotGetByWrongId() {
        Assertions.assertThrows(UserNotFound.class, () -> {
            userService.get(10L);
        });
    }

    @Test
    void shouldUpdateUser() {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setEmail("mulo@m.ru");
        newUserDto.setName("user");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        var result = userService.update(newUserDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldUpdateUser2() {
        NewUserDto newUserDto = new NewUserDto();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        var result = userService.update(newUserDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldUpdateUser3() {
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setEmail("");
        newUserDto.setName("");
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        var result = userService.update(newUserDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldNotUpdateWithWrongId() {
        NewUserDto upUser = new NewUserDto();
        upUser.setEmail("mulo@m.ru");
        upUser.setName("userios");
        Assertions.assertThrows(UserNotFound.class, () -> {
            userService.update(upUser, 10);
        });
    }

    @Test
    void shouldDeleteUser() {
        var userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Assertions.assertEquals(userService.delete(userId), "Пользователь удален");
        verify(userRepository, times(0)).delete(any());
    }

    @Test
    void shouldNotDeleteUser() {
        Assertions.assertThrows(UserNotFound.class, () -> {
            userService.delete(10L);
        });
        verify(userRepository, times(0)).delete(any());
    }
}
