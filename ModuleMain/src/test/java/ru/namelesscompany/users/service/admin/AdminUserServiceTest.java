package ru.namelesscompany.users.service.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.dto.NewUserDto;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;
import ru.namelesscompany.user.service.adminService.AdminUserServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdminUserServiceTest {
    private AdminUserServiceImpl userService;
    private UserRepository userRepository;

    private final User user = new User();

    @BeforeEach
    public void seUp() {
        userRepository = mock(UserRepository.class);
        userService = new AdminUserServiceImpl(userRepository);
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
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    public void shouldGetById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        var result = userService.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldGetAll() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        var result = userService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(user.getId(), result.get(0).getId());
    }

    @Test
    void shouldGetByIds() {
        User user2 = new User();
        user2.setId(2L);
        user2.setName("Name2");
        user2.setEmail("mail2@mail.com");
        when(userRepository.findAll()).thenReturn(List.of(user, user2));
        var result = userService.getAll();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).getId());
        Assertions.assertEquals(2, result.get(1).getId());
    }

    @Test
    void shouldNotGetByWrongId() {
        Assertions.assertThrows(UserNotFound.class, () -> {
            userService.getById(10L);
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
        Assertions.assertEquals(user.getId(), result.getId());
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getName(), result.getName());
    }

    @Test
    void shouldUpdateUser2() {
        NewUserDto newUserDto = new NewUserDto();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        var result = userService.update(newUserDto, 1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(user.getId(), result.getId());
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
        Assertions.assertEquals(user.getId(), result.getId());
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
