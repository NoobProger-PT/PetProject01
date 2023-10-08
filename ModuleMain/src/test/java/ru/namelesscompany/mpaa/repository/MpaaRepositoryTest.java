package ru.namelesscompany.mpaa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.namelesscompany.mpaa.model.Mpaa;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MpaaRepositoryTest {
    @Autowired
    private MpaaRepository mpaaRepository;

    private final Mpaa mpaa = new Mpaa();

    @Test
    public void shouldGetById() {
        createMpaa();
        Mpaa mpaaById = mpaaRepository.findById(1L).get();
        assertThat(mpaaById.getId(), equalTo(mpaa.getId()));
    }

    @Test
    public void shouldAdd() {
        createMpaa();
        assertThat(mpaaRepository.findById(1L).get().getId(), equalTo(mpaa.getId()));
    }

    @Test
    public void shouldDelete() {
        createMpaa();
        assertThat(mpaa.getName(), equalTo(mpaaRepository.findById(1L).get().getName()));
        mpaaRepository.deleteById(1L);
        assertThat(Optional.empty(), equalTo(mpaaRepository.findById(1L)));
    }

    private void createMpaa() {
        mpaa.setId(1L);
        mpaa.setName("Genre 1");
        mpaaRepository.save(mpaa);
    }
}
