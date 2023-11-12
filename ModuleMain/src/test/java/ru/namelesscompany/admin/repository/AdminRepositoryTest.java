package ru.namelesscompany.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.security.role.model.Role;
import ru.namelesscompany.security.role.repository.RoleRepository;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    private final Admin admin = new Admin();

    @Test
    public void shouldGetById() {
        createAdmin();
        Admin adminById = repository.findById(1L).get();
        assertThat(adminById.getId(), equalTo(admin.getId()));
    }

    @Test
    public void shouldFindByEmail() {
        createAdmin();
        Admin adminByEmail = repository.findByEmailContainingIgnoreCase("email@mail.ru");
        assertThat(adminByEmail.getEmail(), equalTo(admin.getEmail()));
    }

    private void createAdmin() {
        roleRepository.save(new Role(1L, "ROLE_ADMIN"));

        admin.setId(1L);
        admin.setName("name");
        admin.setEmail("email@mail.ru");
        admin.setRegistrationDate(LocalDate.of(2022,9,7));
        admin.setPassword("{noop}111");
        admin.setRole(new Role(1L, "ROLE_ADMIN"));
        admin.setOverlord(false);
        repository.save(admin);
    }
}
