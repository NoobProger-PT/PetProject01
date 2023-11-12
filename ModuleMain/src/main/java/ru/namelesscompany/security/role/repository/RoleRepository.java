package ru.namelesscompany.security.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.security.role.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
