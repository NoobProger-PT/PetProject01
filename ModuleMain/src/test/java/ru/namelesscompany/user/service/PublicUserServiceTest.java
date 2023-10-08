package ru.namelesscompany.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.exceptions.user.UserNotFound;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;
import ru.namelesscompany.user.service.privateService.PrivateUserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicUserServiceTest {
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
}
