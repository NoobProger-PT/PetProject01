package ru.namelesscompany.admin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.admin.repository.AdminRepository;
import ru.namelesscompany.security.role.model.Role;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    private AdminServiceImpl service;
    private AdminRepository repository;
    private final Admin admin = new Admin();

    @BeforeEach
    public void setUp() {
        repository = mock(AdminRepository.class);
        service = new AdminServiceImpl(repository);
        when(repository.save(any())).then(invocation -> invocation.getArgument(0));

        admin.setId(1L);
        admin.setName("name");
        admin.setEmail("email@mail.ru");
        admin.setRegistrationDate(LocalDate.of(2022,9,7));
        admin.setPassword("{noop}111");
        admin.setRole(new Role(1L, "ROLE_ADMIN"));
        admin.setOverlord(false);
    }

    @Test
    public void shouldGetById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(admin));
        var result = service.getById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(admin.getId(), result.getId());
        Assertions.assertEquals(admin.getEmail(), result.getEmail());
        Assertions.assertEquals(admin.getName(), result.getName());
    }

    @Test
    public void shouldGetAdminForAll() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(admin));
        var result = service.getAdminForUsers(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(admin.getEmail(), result.getEmail());
        Assertions.assertEquals(admin.getName(), result.getName());
        Assertions.assertEquals(admin.getRegistrationDate(), result.getRegistrationDate());
    }
}
