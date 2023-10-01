package ru.namelesscompany.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.namelesscompany.user.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final User user = new User();

    @Test
    public void shouldGetAll() {
        createUser();
        List<User> users = userRepository.findAll();
        assertThat(users.get(0).getName(), equalTo(user.getName()));
    }

    @Test
    public void shouldGetById() {
        createUser();
        User userById = userRepository.findById(1L).get();
        assertThat(userById.getId(), equalTo(user.getId()));
    }

    @Test
    public void shouldGetByIds() {
        createUser();
        User secondUser= new User();
        secondUser.setId(2L);
        secondUser.setName("Name2");
        secondUser.setEmail("2ndMail@mail.ru");
        secondUser.setRegistrationDate(LocalDate.of(2022,4,7));
        userRepository.save(secondUser);
        assertThat(2, equalTo(userRepository.findAllByIdIn(List.of(1L, 2L)).size()));
    }

    @Test
    public void shouldSave() {
        createUser();
        assertThat(userRepository.findById(1L).get().getId(), equalTo(user.getId()));
    }

    @Test
    public void shouldDelete() {
        createUser();
        assertThat(user.getId(), equalTo(userRepository.findById(1L).get().getId()));
        userRepository.deleteById(1L);
        assertThat(Optional.empty(), equalTo(userRepository.findById(1L)));
    }

    private void createUser() {
        user.setId(1L);
        user.setName("name");
        user.setEmail("email@mail.ru");
        user.setRegistrationDate(LocalDate.of(2022,9,7));
        userRepository.save(user);
    }
}
