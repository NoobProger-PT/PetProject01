package ru.namelesscompany.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> ids);

}
