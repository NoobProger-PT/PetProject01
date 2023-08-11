package ru.namelesscompany.users.service.admin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import ru.namelesscompany.user.model.User;
import ru.namelesscompany.user.repository.UserRepository;
import ru.namelesscompany.user.service.adminService.AdminUserServiceImpl;

import static org.mockito.Mockito.*;

public class AdminUserServiceTest {
    private static AdminUserServiceImpl userService;
    private static UserRepository userRepository;

    private final static User user = new User();

    @BeforeAll
    public static void seUp() {
        userRepository = mock(UserRepository.class);
        userService = new AdminUserServiceImpl(userRepository);
        when(userRepository.save(any())).then(InvocationOnMock::getArguments);

        user.setId(1L);
        user.setName("Name1");
        user.setEmail("mail@mail.com");
    }

    @Test
    public void shouldAddNewUser() {

    }
}
