package ru.namelesscompany.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.namelesscompany.admin.model.Admin;
import ru.namelesscompany.user.model.User;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailContainingIgnoreCase(String email);
}
